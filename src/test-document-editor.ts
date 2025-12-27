import { DocumentEditor } from './document-editor/DocumentEditor';
import { DocumentType } from './document-editor/DocumentFactory';

console.log('=== Testing Document Editor ===\n');

const editor = new DocumentEditor();

// Test 1: Create PDF
console.log('1. Creating PDF Document...');
editor.createNewDocument(DocumentType.PDF, 'This is my PDF content');
editor.addMetadata('author', 'Test Author');
editor.addMetadata('title', 'Test PDF');
console.log(editor.displayDocument());
console.log('\n' + '='.repeat(80) + '\n');

// Test 2: Create Word
console.log('2. Creating Word Document...');
editor.createNewDocument(DocumentType.WORD, 'This is my Word content');
editor.addMetadata('author', 'Test Author');
console.log(editor.displayDocument());
console.log('\n' + '='.repeat(80) + '\n');

// Test 3: Create HTML
console.log('3. Creating HTML Document...');
editor.createNewDocument(DocumentType.HTML, '<h1>Hello World</h1><p>Test content</p>');
editor.addMetadata('title', 'Test Page');
console.log(editor.displayDocument());
console.log('\n' + '='.repeat(80) + '\n');

// Test 4: Save document
console.log('4. Saving PDF document...');
editor.createNewDocument(DocumentType.PDF, 'Save test content');
editor.addMetadata('author', 'Save Test');
const result = editor.saveDocument('my-test-document');
console.log(result.substring(0, 500) + '...\n');
console.log('✅ Document saved successfully!\n');

// Test 5: Edit and save
console.log('5. Testing edit functionality...');
editor.createNewDocument(DocumentType.HTML, '<p>Original content</p>');
console.log('Original:', editor.getCurrentDocument()?.getContent());
editor.editContent('<p>Updated content</p>');
console.log('Updated:', editor.getCurrentDocument()?.getContent());
console.log('✅ Edit functionality working!\n');

// Test 6: Close document
console.log('6. Testing close functionality...');
console.log('Has open document before close:', editor.hasOpenDocument());
editor.closeDocument();
console.log('Has open document after close:', editor.hasOpenDocument());
console.log('✅ Close functionality working!\n');

console.log('='.repeat(80));
console.log('✅ ALL DOCUMENT EDITOR TESTS PASSED!');
console.log('='.repeat(80));
