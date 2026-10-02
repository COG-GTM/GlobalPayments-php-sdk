<?php

declare(strict_types=1);
// Minimal PSR-4 autoloader so the fixture repo runs without composer install (phpunit phar).
spl_autoload_register(static function (string $class): void {
    foreach (['Northgate\\Gateway\\Tests\\' => __DIR__ . '/', 'Northgate\\Gateway\\' => __DIR__ . '/../src/'] as $prefix => $dir) {
        if (str_starts_with($class, $prefix)) {
            $file = $dir . str_replace('\\', '/', substr($class, strlen($prefix))) . '.php';
            if (is_file($file)) {
                require $file;
            }
            return;
        }
    }
});
