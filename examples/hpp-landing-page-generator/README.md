# HPP Landing Page Generator

A white-label Hosted Payment Page (HPP) landing page generator for Global Payments. This tool generates brandable HTML landing pages from a JSON configuration file, making it easy to create custom payment pages for your business.

## Features

- Generate fully branded payment landing pages
- Customizable colors, fonts, and logos
- Integration with Global Payments Hosted Payment Pages
- Responsive design that works on all devices
- Production-ready HTML and CSS output
- Secure payment form with proper escaping

## Requirements

- PHP 8.0 or higher
- Global Payments PHP SDK
- Valid Global Payments merchant credentials

## Installation

1. Clone the repository or navigate to the examples directory:
   ```bash
   cd examples/hpp-landing-page-generator
   ```

2. Ensure the Global Payments PHP SDK is properly installed (composer install from the root directory)

## Configuration

Edit the `config.json` file to customize your landing page:

```json
{
  "brand": {
    "name": "Your Company",
    "logo_url": "https://example.com/logo.png",
    "primary_color": "#1a73e8",
    "secondary_color": "#ffffff",
    "font_family": "Arial, sans-serif"
  },
  "payment": {
    "merchant_id": "YOUR_MERCHANT_ID",
    "account": "YOUR_ACCOUNT",
    "shared_secret": "YOUR_SHARED_SECRET",
    "currency": "USD",
    "amount": "100.00"
  },
  "allowed_payment_methods": ["CARDS", "OB"],
  "page": {
    "title": "Secure Payment",
    "description": "Complete your payment securely",
    "success_url": "https://example.com/success",
    "cancel_url": "https://example.com/cancel"
  }
}
```

### Configuration Options

#### Brand Settings
- **name**: Your company or brand name
- **logo_url**: URL to your logo image (PNG, JPG, or SVG)
- **primary_color**: Primary brand color (hex format)
- **secondary_color**: Secondary color, typically white or light color (hex format)
- **font_family**: CSS font family for the page

#### Payment Settings
- **merchant_id**: Your Global Payments merchant ID
- **account**: Your Global Payments account ID
- **shared_secret**: Your Global Payments shared secret
- **currency**: Three-letter currency code (USD, EUR, GBP, etc.)
- **amount**: Payment amount in decimal format

#### Allowed Payment Methods
Array of payment method types:
- **CARDS**: Credit/debit cards
- **OB**: Open Banking

#### Page Settings
- **title**: Page title displayed in browser and header
- **description**: Brief description shown below the title
- **success_url**: URL to redirect after successful payment
- **cancel_url**: URL to redirect if payment is cancelled

## Usage

Run the generator from the command line:

```bash
php generate.php
```

The generator will:
1. Read your configuration from `config.json`
2. Generate a styled landing page with your branding
3. Create HPP integration fields using the Global Payments SDK
4. Output files to the `output/` directory:
   - `index.html` - The landing page
   - `styles.css` - Branded stylesheet

## Output

After running the generator, you'll find two files in the `output/` directory:

- **index.html**: Complete HTML landing page with embedded payment form
- **styles.css**: Responsive stylesheet with your brand colors and fonts

## Deployment

To deploy your generated landing page:

1. Upload both `index.html` and `styles.css` to your web server
2. Ensure both files are in the same directory
3. Configure your web server to serve the files over HTTPS
4. Test the payment flow in sandbox mode before going live

## Customization

### Templates

The generator uses three template files in the `templates/` directory:

- **header.html**: Page header with logo and title
- **payment-form.html**: Payment form and details
- **footer.html**: Page footer

You can modify these templates to change the page structure. Use the following placeholders:

- `{{PAGE_TITLE}}` - Page title
- `{{PAGE_DESCRIPTION}}` - Page description
- `{{LOGO_URL}}` - Brand logo URL
- `{{BRAND_NAME}}` - Brand name
- `{{CURRENCY}}` - Payment currency
- `{{AMOUNT}}` - Payment amount
- `{{HPP_URL}}` - HPP form action URL
- `{{HPP_FIELDS}}` - HPP hidden form fields
- `{{CANCEL_URL}}` - Cancel redirect URL
- `{{CURRENT_YEAR}}` - Current year

### Styling

The generator creates a `styles.css` file based on your brand configuration. You can further customize the styles by:

1. Generating the initial files
2. Editing `output/styles.css` directly
3. Adding custom CSS rules as needed

## Security Considerations

### Important Security Notes

1. **Never commit credentials**: Keep your `config.json` file out of version control if it contains real credentials
2. **Use HTTPS**: Always serve payment pages over HTTPS in production
3. **Validate inputs**: The generator properly escapes all user inputs to prevent XSS attacks
4. **Secure your shared secret**: Never expose your shared secret in client-side code
5. **Test in sandbox**: Always test with sandbox credentials before using production credentials

### Best Practices

- Store sensitive configuration in environment variables
- Use different credentials for development and production
- Regularly rotate your API credentials
- Monitor your payment transactions for suspicious activity
- Keep the Global Payments PHP SDK up to date

## Troubleshooting

### Configuration file not found
Ensure `config.json` exists in the same directory as `generate.php`

### Invalid JSON error
Validate your JSON syntax using a JSON validator

### Permission denied when creating output directory
Ensure the script has write permissions in the current directory

### HPP fields not generating
Verify your merchant credentials are correct and you have network access to the Global Payments API

## Example

Here's a complete example workflow:

```bash
# 1. Edit configuration
nano config.json

# 2. Generate landing page
php generate.php

# 3. View the output
ls -la output/

# 4. Test locally (requires a web server)
php -S localhost:8000 -t output/

# 5. Deploy to production
scp output/* user@yourserver:/var/www/html/payment/
```

## Support

For issues with:
- **This generator**: Check the Global Payments PHP SDK documentation
- **Global Payments API**: Contact Global Payments support
- **Payment processing**: Refer to Global Payments merchant documentation

## License

This example is provided as part of the Global Payments PHP SDK and follows the same license terms.
