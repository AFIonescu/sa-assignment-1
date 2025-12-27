/**
 * Abstract Document class - defines the interface for all document types
 */
export abstract class Document {
  protected content: string;
  protected metadata: Map<string, string>;

  constructor(content: string = '') {
    this.content = content;
    this.metadata = new Map();
  }

  /**
   * Save the document in its specific format
   */
  abstract save(filePath: string): string;

  /**
   * Display the document content
   */
  abstract display(): string;

  /**
   * Get the file extension for this document type
   */
  abstract getFileExtension(): string;

  /**
   * Set content of the document
   */
  setContent(content: string): void {
    this.content = content;
  }

  /**
   * Get content of the document
   */
  getContent(): string {
    return this.content;
  }

  /**
   * Add metadata to the document
   */
  addMetadata(key: string, value: string): void {
    this.metadata.set(key, value);
  }

  /**
   * Get metadata from the document
   */
  getMetadata(key: string): string | undefined {
    return this.metadata.get(key);
  }

  /**
   * Get all metadata
   */
  getAllMetadata(): Map<string, string> {
    return new Map(this.metadata);
  }
}
