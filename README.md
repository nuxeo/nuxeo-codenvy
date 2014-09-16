Work in Progress
====================================


Nuxeo Plugin for Codenvy IDE 3
====================================

Description
-----------

This project is a plugin for [Codenvy IDE 3](http://docs.codenvy.com/sdk/introduction/) that allows to work with Nuxeo Platform directly from within the IDE.

About Nuxeo
-----------

Nuxeo provides a modular, extensible Java-based
[open source software platform for enterprise content management](http://www.nuxeo.com/en/products/ep),
and packaged applications for [document management](http://www.nuxeo.com/en/products/document-management),
[digital asset management](http://www.nuxeo.com/en/products/dam) and
[case management](http://www.nuxeo.com/en/products/case-management).

Designed by developers for developers, the Nuxeo platform offers a modern
architecture, a powerful plug-in model and extensive packaging
capabilities for building content applications.

More information on: <http://www.nuxeo.com/>

What it can do
--------------

With this plugin, you can :

- create a Nuxeo server-side bundle to add features such as [Automation Operations](http://doc.nuxeo.com/x/mQAz).
- create a Nuxeo AngularJS client application to access to Nuxeo platform through REST API.
- push your projects into Nuxeo IO instance or directly into Codenvy Nuxeo Runner.

Dev
------------

mvn clean install license:format -Dlicense.header=header.txt to add licence headers for mvn headers check in case.
Pay attention to the sort of group maven artifact (artifactId should be placed before groupId) -> maven sort check.

Documentation
-------------

