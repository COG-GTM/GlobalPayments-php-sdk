<?php

namespace GlobalPayments\Api\Tests\Unit\Utils;

use GlobalPayments\Api\Terminals\Enums\ControlCodes;
use GlobalPayments\Api\Terminals\PAX\SubGroups\AccountRequest;
use GlobalPayments\Api\Utils\Logging\TerminalLogMasking;
use PHPUnit\Framework\TestCase;

class TerminalLogMaskingTest extends TestCase
{
    public function testMasksPaxAccountSubGroup()
    {
        $accountRequest = new AccountRequest();
        $accountRequest->accountNumber = '4111111111111111';
        $accountRequest->expd = '1225';
        $accountRequest->cvvCode = '123';

        $masked = TerminalLogMasking::maskMessage(
            'Input Message: ' . $accountRequest->getElementString()
        );

        $this->assertStringNotContainsString('4111111111111111', $masked);
        $this->assertStringNotContainsString('1225', $masked);
        $this->assertStringNotContainsString('123', $masked);
        $this->assertStringContainsString('411111XXXXXX1111', $masked);
    }

    public function testMasksStandAloneAccountNumber()
    {
        $masked = TerminalLogMasking::maskMessage('Device Response : 5473500000000014 approved');

        $this->assertSame('Device Response : 547350XXXXXX0014 approved', $masked);
    }

    public function testRedactsTrackData()
    {
        $masked = TerminalLogMasking::maskMessage('%B4111111111111111^DOE/JOHN^25121011000000?');

        $this->assertStringNotContainsString('4111111111111111', $masked);
        $this->assertStringNotContainsString('DOE/JOHN', $masked);
    }

    public function testRedactsSensitiveJsonKeys()
    {
        $masked = TerminalLogMasking::maskMessage(
            '{"cardNumber":"4111111111111111","cvv":"123","expiry":"1225","amount":"10.00"}'
        );

        $this->assertStringNotContainsString('4111111111111111', $masked);
        $this->assertStringNotContainsString('"123"', $masked);
        $this->assertStringNotContainsString('"1225"', $masked);
        $this->assertStringContainsString('"amount":"10.00"', $masked);
    }

    public function testLeavesNonCardDataUntouched()
    {
        $message = 'A00' . chr(ControlCodes::FS) . '1.35' . chr(ControlCodes::FS) . 'amount:1000';

        $this->assertSame($message, TerminalLogMasking::maskMessage($message));
    }
}
