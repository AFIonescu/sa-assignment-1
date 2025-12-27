import { DocumentFactory, DocumentType } from '../../document-editor/DocumentFactory';
import { PDFDocument } from '../../document-editor/PDFDocument';
import { WordDocument } from '../../document-editor/WordDocument';
import { HTMLDocument } from '../../document-editor/HTMLDocument';

describe('DocumentFactory', () => {
  test('should create a PDF document', () => {
    const doc = DocumentFactory.createDocument(DocumentType.PDF, 'PDF content');
    expect(doc).toBeInstanceOf(PDFDocument);
    expect(doc.getContent()).toBe('PDF content');
  });

  test('should create a Word document', () => {
    const doc = DocumentFactory.createDocument(DocumentType.WORD, 'Word content');
    expect(doc).toBeInstanceOf(WordDocument);
    expect(doc.getContent()).toBe('Word content');
  });

  test('should create an HTML document', () => {
    const doc = DocumentFactory.createDocument(DocumentType.HTML, '<p>HTML content</p>');
    expect(doc).toBeInstanceOf(HTMLDocument);
    expect(doc.getContent()).toBe('<p>HTML content</p>');
  });

  test('should create a document with empty content if not provided', () => {
    const doc = DocumentFactory.createDocument(DocumentType.PDF);
    expect(doc.getContent()).toBe('');
  });

  test('should throw error for unsupported document type', () => {
    expect(() => {
      DocumentFactory.createDocument('INVALID' as DocumentType);
    }).toThrow('Unsupported document type: INVALID');
  });

  test('should return all supported document types', () => {
    const types = DocumentFactory.getSupportedTypes();
    expect(types).toContain(DocumentType.PDF);
    expect(types).toContain(DocumentType.WORD);
    expect(types).toContain(DocumentType.HTML);
    expect(types.length).toBe(3);
  });

  test('should check if a document type is supported', () => {
    expect(DocumentFactory.isTypeSupported('PDF')).toBe(true);
    expect(DocumentFactory.isTypeSupported('WORD')).toBe(true);
    expect(DocumentFactory.isTypeSupported('HTML')).toBe(true);
    expect(DocumentFactory.isTypeSupported('INVALID')).toBe(false);
  });
});
