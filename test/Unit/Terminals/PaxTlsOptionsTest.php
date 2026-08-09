<?php

namespace GlobalPayments\Api\Tests\Unit\Terminals;

use GlobalPayments\Api\Entities\Exceptions\ConfigurationException;
use GlobalPayments\Api\Terminals\ConnectionConfig;
use GlobalPayments\Api\Terminals\Enums\ConnectionModes;
use GlobalPayments\Api\Terminals\Enums\DeviceType;
use GlobalPayments\Api\Terminals\PAX\Interfaces\PaxHttpInterface;
use GlobalPayments\Api\Terminals\PAX\Interfaces\PaxTcpInterface;
use PHPUnit\Framework\TestCase;

class PaxTlsOptionsTest extends TestCase
{
    private function config($connectionMode)
    {
        $config = new ConnectionConfig();
        $config->deviceType = DeviceType::PAX_S300;
        $config->connectionMode = $connectionMode;
        $config->ipAddress = '10.0.0.1';
        $config->port = '10009';
        $config->timeout = 30;

        return $config;
    }

    private function invokePrivate($object, $method)
    {
        $reflection = new \ReflectionMethod(get_class($object), $method);
        $reflection->setAccessible(true);

        return $reflection->invoke($object);
    }

    public function testTcpVerifiesPeerByDefault()
    {
        $options = $this->invokePrivate(
            new PaxTcpInterface($this->config(ConnectionModes::SSL_TCP)),
            'getSslOptions'
        );

        $this->assertTrue($options['verify_peer']);
        $this->assertTrue($options['verify_peer_name']);
        $this->assertFalse($options['allow_self_signed']);
        $this->assertArrayNotHasKey('cafile', $options);
        $this->assertArrayNotHasKey('peer_fingerprint', $options);
    }

    public function testTcpSelfSignedRequiresAnchor()
    {
        $config = $this->config(ConnectionModes::SSL_TCP);
        $config->allowSelfSignedCertificate = true;

        $this->expectException(ConfigurationException::class);
        $this->invokePrivate(new PaxTcpInterface($config), 'getSslOptions');
    }

    public function testTcpSelfSignedAllowedWhenPinned()
    {
        $config = $this->config(ConnectionModes::SSL_TCP);
        $config->allowSelfSignedCertificate = true;
        $config->sslPeerFingerprint = ['sha256' => str_repeat('a', 64)];
        $config->sslPeerName = 'terminal.local';

        $options = $this->invokePrivate(new PaxTcpInterface($config), 'getSslOptions');

        $this->assertTrue($options['verify_peer']);
        $this->assertTrue($options['verify_peer_name']);
        $this->assertTrue($options['allow_self_signed']);
        $this->assertSame(['sha256' => str_repeat('a', 64)], $options['peer_fingerprint']);
        $this->assertSame('terminal.local', $options['peer_name']);
    }

    public function testHttpsVerifiesPeerByDefault()
    {
        $options = $this->invokePrivate(
            new PaxHttpInterface($this->config(ConnectionModes::HTTPS)),
            'getCurlOptions'
        );

        $this->assertTrue($options[CURLOPT_SSL_VERIFYPEER]);
        $this->assertSame(2, $options[CURLOPT_SSL_VERIFYHOST]);
        $this->assertArrayNotHasKey(CURLOPT_CAINFO, $options);
    }

    public function testHttpsSelfSignedRequiresCaFile()
    {
        $config = $this->config(ConnectionModes::HTTPS);
        $config->allowSelfSignedCertificate = true;

        $this->expectException(ConfigurationException::class);
        $this->invokePrivate(new PaxHttpInterface($config), 'getCurlOptions');
    }

    public function testHttpsUsesConfiguredCaFile()
    {
        $config = $this->config(ConnectionModes::HTTPS);
        $config->allowSelfSignedCertificate = true;
        $config->sslCaFile = '/etc/ssl/certs/terminal-ca.pem';

        $options = $this->invokePrivate(new PaxHttpInterface($config), 'getCurlOptions');

        $this->assertTrue($options[CURLOPT_SSL_VERIFYPEER]);
        $this->assertSame('/etc/ssl/certs/terminal-ca.pem', $options[CURLOPT_CAINFO]);
    }
}
