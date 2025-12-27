import { DocumentEditor } from '../../document-editor/DocumentEditor';
import { DocumentType } from '../../document-editor/DocumentFactory';

describe('DocumentEditor', () => {
  let editor: DocumentEditor;

  beforeEach(() => {
    editor = new DocumentEditor();
  });

  test('should create a new document', () => {
    editor.createNewDocument(DocumentType.PDF, 'Initial content');
    expect(editor.hasOpenDocument()).toBe(true);
    expect(editor.getCurrentDocument()?.getContent()).toBe('Initial content');
  });

  test('should create document with empty content if not provided', () => {
    editor.createNewDocument(DocumentType.WORD);
    expect(editor.hasOpenDocument()).toBe(true);
    expect(editor.getCurrentDocument()?.getContent()).toBe('');
  });

  test('should edit content of current document', () => {
    editor.createNewDocument(DocumentType.HTML);
    editor.editContent('<p>New content</p>');
    expect(editor.getCurrentDocument()?.getContent()).toBe('<p>New content</p>');
  });

  test('should throw error when editing without an open document', () => {
    expect(() => {
      editor.editContent('Content');
    }).toThrow('No document is currently open');
  });

  test('should add metadata to current document', () => {
    editor.createNewDocument(DocumentType.PDF);
    editor.addMetadata('author', 'John Doe');
    expect(editor.getCurrentDocument()?.getMetadata('author')).toBe('John Doe');
  });

  test('should throw error when adding metadata without an open document', () => {
    expect(() => {
      editor.addMetadata('author', 'John');
    }).toThrow('No document is currently open');
  });

  test('should display the current document', () => {
    editor.createNewDocument(DocumentType.PDF, 'Test content');
    const display = editor.displayDocument();
    expect(display).toContain('[PDF Document]');
    expect(display).toContain('Test content');
  });

  test('should throw error when displaying without an open document', () => {
    expect(() => {
      editor.displayDocument();
    }).toThrow('No document is currently open');
  });

  test('should save the current document', () => {
    editor.createNewDocument(DocumentType.WORD, 'Save test');
    const result = editor.saveDocument('test');
    expect(result).toContain('Word document saved to test.docx');
  });

  test('should save document with correct extension even if provided', () => {
    editor.createNewDocument(DocumentType.PDF);
    const result = editor.saveDocument('test.pdf');
    expect(result).toContain('PDF document saved to test.pdf');
  });

  test('should append extension if not provided', () => {
    editor.createNewDocument(DocumentType.HTML);
    const result = editor.saveDocument('test');
    expect(result).toContain('HTML document saved to test.html');
  });

  test('should throw error when saving without an open document', () => {
    expect(() => {
      editor.saveDocument('test');
    }).toThrow('No document is currently open');
  });

  test('should close the current document', () => {
    editor.createNewDocument(DocumentType.PDF);
    expect(editor.hasOpenDocument()).toBe(true);
    editor.closeDocument();
    expect(editor.hasOpenDocument()).toBe(false);
    expect(editor.getCurrentDocument()).toBeNull();
  });

  test('should allow creating a new document after closing', () => {
    editor.createNewDocument(DocumentType.PDF, 'First');
    editor.closeDocument();
    editor.createNewDocument(DocumentType.WORD, 'Second');
    expect(editor.getCurrentDocument()?.getContent()).toBe('Second');
  });

  test('should return null for current document when none is open', () => {
    expect(editor.getCurrentDocument()).toBeNull();
  });

  test('should handle multiple operations in sequence', () => {
    editor.createNewDocument(DocumentType.HTML);
    editor.editContent('<h1>Title</h1>');
    editor.addMetadata('title', 'My Page');
    editor.addMetadata('author', 'Developer');

    const display = editor.displayDocument();
    expect(display).toContain('<h1>Title</h1>');
    expect(display).toContain('title: My Page');

    const saved = editor.saveDocument('mypage');
    expect(saved).toContain('HTML document saved to mypage.html');
    expect(saved).toContain('<title>My Page</title>');
  });
});
