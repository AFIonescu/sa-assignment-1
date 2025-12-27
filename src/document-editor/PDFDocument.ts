import { Document } from './Document';

/**
 * Concrete implementation for PDF documents
 */
export class PDFDocument extends Document {
  save(filePath: string): string {
    const metadata = Array.from(this.metadata.entries())
      .map(([key, value]) => `${key}: ${value}`)
      .join('\n');

    const pdfContent = `%PDF-1.4
%Header
1 0 obj
<<
/Type /Catalog
/Pages 2 0 R
/Metadata <<
${metadata}
>>
>>
endobj

2 0 obj
<<
/Type /Pages
/Kids [3 0 R]
/Count 1
>>
endobj

3 0 obj
<<
/Type /Page
/Parent 2 0 R
/Contents 4 0 R
>>
endobj

4 0 obj
<<
/Length ${this.content.length}
>>
stream
${this.content}
endstream
endobj

xref
0 5
trailer
<<
/Size 5
/Root 1 0 R
>>
%%EOF`;

    return `PDF document saved to ${filePath}\n${pdfContent}`;
  }

  display(): string {
    const metadataStr = Array.from(this.metadata.entries())
      .map(([key, value]) => `  ${key}: ${value}`)
      .join('\n');

    return `[PDF Document]
Format: Portable Document Format
Metadata:
${metadataStr || '  None'}

Content:
${this.content}
================`;
  }

  getFileExtension(): string {
    return '.pdf';
  }
}
