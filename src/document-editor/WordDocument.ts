import { Document } from './Document';

/**
 * Concrete implementation for Word documents
 */
export class WordDocument extends Document {
  save(filePath: string): string {
    const metadata = Array.from(this.metadata.entries())
      .map(([key, value]) => `    <meta name="${key}" content="${value}"/>`)
      .join('\n');

    const wordXml = `<?xml version="1.0" encoding="UTF-8"?>
<w:document xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main">
  <w:metadata>
${metadata}
  </w:metadata>
  <w:body>
    <w:p>
      <w:r>
        <w:t>${this.escapeXml(this.content)}</w:t>
      </w:r>
    </w:p>
  </w:body>
</w:document>`;

    return `Word document saved to ${filePath}\n${wordXml}`;
  }

  display(): string {
    const metadataStr = Array.from(this.metadata.entries())
      .map(([key, value]) => `  ${key}: ${value}`)
      .join('\n');

    return `[Word Document]
Format: Microsoft Word (.docx)
Metadata:
${metadataStr || '  None'}

Content:
${this.content}
================`;
  }

  getFileExtension(): string {
    return '.docx';
  }

  private escapeXml(text: string): string {
    return text
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;')
      .replace(/'/g, '&apos;');
  }
}
