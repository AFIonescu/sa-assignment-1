package org.example.creational.factory;

public class HtmlDocument implements Document {
    private String content = "";

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void open() {
        System.out.println("Opening HTML document in browser");
    }

    @Override
    public void save() {
        System.out.println("Saving HTML document to .html file");
    }

    @Override
    public void display() {
        System.out.println("<html>");
        System.out.println("<head><title>Document</title></head>");
        System.out.println("<body>");
        System.out.println("<p>" + content + "</p>");
        System.out.println("</body>");
        System.out.println("</html>");
    }
}
