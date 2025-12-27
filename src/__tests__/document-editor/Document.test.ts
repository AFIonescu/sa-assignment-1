import { PDFDocument } from '../../document-editor/PDFDocument';
import { WordDocument } from '../../document-editor/WordDocument';
import { HTMLDocument } from '../../document-editor/HTMLDocument';

describe('Document Classes', () => {
  describe('PDFDocument', () => {
    let pdfDoc: PDFDocument;

    beforeEach(() => {
      pdfDoc = new PDFDocument('Test content for PDF');
    });

    test('should create a PDF document with content', () => {
      expect(pdfDoc.getContent()).toBe('Test content for PDF');
    });

    test('should set and get content', () => {
      pdfDoc.setContent('New PDF content');
      expect(pdfDoc.getContent()).toBe('New PDF content');
    });

    test('should add and retrieve metadata', () => {
      pdfDoc.addMetadata('author', 'John Doe');
      pdfDoc.addMetadata('title', 'Test Document');
      expect(pdfDoc.getMetadata('author')).toBe('John Doe');
      expect(pdfDoc.getMetadata('title')).toBe('Test Document');
    });

    test('should return correct file extension', () => {
      expect(pdfDoc.getFileExtension()).toBe('.pdf');
    });

    test('should save document with PDF format', () => {
      pdfDoc.addMetadata('author', 'Test Author');
      const result = pdfDoc.save('test.pdf');
      expect(result).toContain('PDF document saved to test.pdf');
      expect(result).toContain('%PDF-1.4');
      expect(result).toContain('Test content for PDF');
    });

    test('should display document information', () => {
      pdfDoc.addMetadata('author', 'Test Author');
      const display = pdfDoc.display();
      expect(display).toContain('[PDF Document]');
      expect(display).toContain('author: Test Author');
      expect(display).toContain('Test content for PDF');
    });

    test('should get all metadata', () => {
      pdfDoc.addMetadata('author', 'John');
      pdfDoc.addMetadata('year', '2025');
      const metadata = pdfDoc.getAllMetadata();
      expect(metadata.size).toBe(2);
      expect(metadata.get('author')).toBe('John');
      expect(metadata.get('year')).toBe('2025');
    });
  });

  describe('WordDocument', () => {
    let wordDoc: WordDocument;

    beforeEach(() => {
      wordDoc = new WordDocument('Test Word content');
    });

    test('should create a Word document with content', () => {
      expect(wordDoc.getContent()).toBe('Test Word content');
    });

    test('should return correct file extension', () => {
      expect(wordDoc.getFileExtension()).toBe('.docx');
    });

    test('should save document with Word XML format', () => {
      wordDoc.addMetadata('author', 'Jane Doe');
      const result = wordDoc.save('test.docx');
      expect(result).toContain('Word document saved to test.docx');
      expect(result).toContain('<?xml version="1.0" encoding="UTF-8"?>');
      expect(result).toContain('<w:document');
      expect(result).toContain('Test Word content');
    });

    test('should escape XML special characters in content', () => {
      wordDoc.setContent('Test <tag> & "quotes"');
      const result = wordDoc.save('test.docx');
      expect(result).toContain('&lt;tag&gt;');
      expect(result).toContain('&amp;');
      expect(result).toContain('&quot;');
    });

    test('should display document information', () => {
      const display = wordDoc.display();
      expect(display).toContain('[Word Document]');
      expect(display).toContain('Microsoft Word');
      expect(display).toContain('Test Word content');
    });
  });

  describe('HTMLDocument', () => {
    let htmlDoc: HTMLDocument;

    beforeEach(() => {
      htmlDoc = new HTMLDocument('<h1>Test HTML</h1>');
    });

    test('should create an HTML document with content', () => {
      expect(htmlDoc.getContent()).toBe('<h1>Test HTML</h1>');
    });

    test('should return correct file extension', () => {
      expect(htmlDoc.getFileExtension()).toBe('.html');
    });

    test('should save document with HTML format', () => {
      htmlDoc.addMetadata('title', 'Test Page');
      htmlDoc.addMetadata('author', 'Web Developer');
      const result = htmlDoc.save('test.html');
      expect(result).toContain('HTML document saved to test.html');
      expect(result).toContain('<!DOCTYPE html>');
      expect(result).toContain('<h1>Test HTML</h1>');
      expect(result).toContain('<title>Test Page</title>');
    });

    test('should use default title if not provided', () => {
      const result = htmlDoc.save('test.html');
      expect(result).toContain('<title>Document</title>');
    });

    test('should display document information', () => {
      const display = htmlDoc.display();
      expect(display).toContain('[HTML Document]');
      expect(display).toContain('HyperText Markup Language');
      expect(display).toContain('<h1>Test HTML</h1>');
    });
  });
});
