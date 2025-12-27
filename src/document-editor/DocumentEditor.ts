import { Document } from './Document';
import { DocumentFactory, DocumentType } from './DocumentFactory';

/**
 * Document Editor class
 * The core editor logic is not coupled to specific document types
 * Uses DocumentFactory to create appropriate document instances
 */
export class DocumentEditor {
  private currentDocument: Document | null = null;

  /**
   * Create a new document of the specified type
   */
  createNewDocument(type: DocumentType, content: string = ''): void {
    this.currentDocument = DocumentFactory.createDocument(type, content);
  }

  /**
   * Get the current document
   */
  getCurrentDocument(): Document | null {
    return this.currentDocument;
  }

  /**
   * Edit the current document's content
   */
  editContent(content: string): void {
    if (!this.currentDocument) {
      throw new Error('No document is currently open. Create or open a document first.');
    }
    this.currentDocument.setContent(content);
  }

  /**
   * Add metadata to the current document
   */
  addMetadata(key: string, value: string): void {
    if (!this.currentDocument) {
      throw new Error('No document is currently open. Create or open a document first.');
    }
    this.currentDocument.addMetadata(key, value);
  }

  /**
   * Display the current document
   */
  displayDocument(): string {
    if (!this.currentDocument) {
      throw new Error('No document is currently open. Create or open a document first.');
    }
    return this.currentDocument.display();
  }

  /**
   * Save the current document
   */
  saveDocument(filePath: string): string {
    if (!this.currentDocument) {
      throw new Error('No document is currently open. Create or open a document first.');
    }

    const fullPath = filePath.endsWith(this.currentDocument.getFileExtension())
      ? filePath
      : filePath + this.currentDocument.getFileExtension();

    return this.currentDocument.save(fullPath);
  }

  /**
   * Close the current document
   */
  closeDocument(): void {
    this.currentDocument = null;
  }

  /**
   * Check if a document is currently open
   */
  hasOpenDocument(): boolean {
    return this.currentDocument !== null;
  }
}
