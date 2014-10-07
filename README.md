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

- Create a Nuxeo server-side bundle to add features such as [Automation Operations](http://doc.nuxeo.com/x/mQAz).
- The project generation adds AngularJS client application sample to browse Nuxeo repository.
- To deploy bundle into Nuxeo Docker Runner (Connect Authentication provided).

Code & Deployment
------------

- All code remains into nuxeo-codenvy-client.
- mvn clean install in root to build Nuxeo plugin.
- Add into assembly-ide/pom.xml (gwt deployment):

	    <dependency>
	      <groupId>org.nuxeo.codenvy</groupId>
	      <artifactId>nuxeo-codenvy-client</artifactId>
	      <version>1.0.0-SNAPSHOT<</version>
	    </dependency>

- Add into assembly-ide/src/main/resources/com/codenvy/ide/IDE.gwt.xml (gwt deployment):

		<inherits name="org.nuxeo.codenvy.Nuxeo"/>

- Add into assembly-platform-api/pom.xml (server deployment)

	    <dependency>
	      <groupId>org.nuxeo.codenvy</groupId>
	      <artifactId>nuxeo-codenvy-client</artifactId>
	      <version>1.0.0-SNAPSHOT<</version>
	    </dependency>

User Documentation (in progress)
-------------

- Create a Nuxeo project:

- Create an Automation operation:

- Authentication through Nuxeo Connect:

- Deploy project into Nuxeo Docker Runner:



