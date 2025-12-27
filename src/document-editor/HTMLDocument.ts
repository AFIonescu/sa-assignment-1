import { Document } from './Document';

/**
 * Concrete implementation for HTML documents
 */
export class HTMLDocument extends Document {
  save(filePath: string): string {
    const metadata = Array.from(this.metadata.entries())
      .map(([key, value]) => `    <meta name="${key}" content="${value}">`)
      .join('\n');

    const htmlContent = `<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
${metadata}
    <title>${this.metadata.get('title') || 'Document'}</title>
</head>
<body>
    ${this.content}
</body>
</html>`;

    return `HTML document saved to ${filePath}\n${htmlContent}`;
  }

  display(): string {
    const metadataStr = Array.from(this.metadata.entries())
      .map(([key, value]) => `  ${key}: ${value}`)
      .join('\n');

    return `[HTML Document]
Format: HyperText Markup Language
Metadata:
${metadataStr || '  None'}

Content:
${this.content}
================`;
  }

  getFileExtension(): string {
    return '.html';
  }
}
