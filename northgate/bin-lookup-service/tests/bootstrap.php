<?php

declare(strict_types=1);
spl_autoload_register(static function (string $class): void {
    foreach (['Northgate\\BinLookup\\Tests\\' => __DIR__ . '/', 'Northgate\\BinLookup\\' => __DIR__ . '/../src/'] as $prefix => $dir) {
        if (str_starts_with($class, $prefix)) {
            $file = $dir . str_replace('\\', '/', substr($class, strlen($prefix))) . '.php';
            if (is_file($file)) {
                require $file;
            }
            return;
        }
    }
});
