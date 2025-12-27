import { Document } from './Document';
import { PDFDocument } from './PDFDocument';
import { WordDocument } from './WordDocument';
import { HTMLDocument } from './HTMLDocument';

/**
 * Document types supported by the factory
 */
export enum DocumentType {
  PDF = 'PDF',
  WORD = 'WORD',
  HTML = 'HTML'
}

/**
 * Factory class for creating documents
 * Implements the Factory Pattern to decouple document creation from the editor
 */
export class DocumentFactory {
  /**
   * Create a document based on the specified type
   * @param type - The type of document to create
   * @param content - Optional initial content for the document
   * @returns A new document instance
   */
  static createDocument(type: DocumentType, content: string = ''): Document {
    switch (type) {
      case DocumentType.PDF:
        return new PDFDocument(content);
      case DocumentType.WORD:
        return new WordDocument(content);
      case DocumentType.HTML:
        return new HTMLDocument(content);
      default:
        throw new Error(`Unsupported document type: ${type}`);
    }
  }

  /**
   * Get all supported document types
   */
  static getSupportedTypes(): DocumentType[] {
    return Object.values(DocumentType);
  }

  /**
   * Check if a document type is supported
   */
  static isTypeSupported(type: string): boolean {
    return Object.values(DocumentType).includes(type as DocumentType);
  }
}
