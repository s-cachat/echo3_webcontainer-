module echo3.webcontainer{
    requires echo3.app;
    requires jakarta.servlet;
    requires java.xml;
    requires java.desktop;
    requires java.logging;
    exports nextapp.echo.webcontainer;
    exports nextapp.echo.webcontainer.command;
    exports nextapp.echo.webcontainer.service;
    exports nextapp.echo.webcontainer.util;
    exports nextapp.echo.webcontainer.sync.component;
}

