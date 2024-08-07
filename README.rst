OpaqueToJwt Token Procedure Plugin
=============================================

.. image:: https://img.shields.io/badge/quality-experiment-red
    :target: https://curity.io/resources/code-examples/status/
.. image:: https://img.shields.io/badge/availability-source-blue
    :target: https://curity.io/resources/code-examples/status/

A custom Token Procedure plugin for the Curity Identity Server. The plugin uses the Token Exchange (`RFC 8693 <https://datatracker.ietf.org/doc/html/rfc8693>`_) flow to exchange an opaque token to a JWT.

Building the Plugin
~~~~~~~~~~~~~~~~~~~

You can build the plugin by issuing the command ``mvn package``. This will produce a JAR file in the ``target`` directory,
which can be installed.

Installing the Plugin
~~~~~~~~~~~~~~~~~~~~~

To install the plugin, copy the compiled JAR into the ``${IDSVR_HOME}/usr/share/plugins/opaquetojwt``
on each node, including the admin node. For more information about installing plugins, refer to the `curity.io/plugins`_.

Configuring the Plugin
~~~~~~~~~~~~~~~~~~~~~~

The plugin needs to be configured and assigned. This plugin does not have any settings. The `Documentation <https://curity.io/docs/idsvr/latest/token-service-admin-guide/token-procedure-plugins.html#token-procedure-plugins-1>`_ describes how to set it up. This plugin should be assigned to the ``Token OAuth Token Exchange`` flow on a ``oauth-token`` endpoint.

Using the Plugin
~~~~~~~~~~~~~~~~~~~~~~

The plugin is used by sending a token exchange request to the ``oauth-token`` endpoint. An example can look like:

::

  curl -Ss -X POST \
  https://idsvr.example.com/oauth/v2/oauth-token \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'grant_type=urn:ietf:params:oauth:grant-type:token-exchange' \
  -d 'client_id=clienta' \
  -d 'client_secret=aaaaaa' \
  -d 'subject_token_type=urn:ietf:params:oauth:token-type:access_token' \
  -d 'subject_token=_0XBPWQQ...'

More Information
~~~~~~~~~~~~~~~~

Please visit `curity.io`_ for more information about the Curity Identity Server.

.. _curity.io/plugins: https://curity.io/docs/idsvr/latest/developer-guide/plugins/index.html#plugin-installation
.. _curity.io: https://curity.io/
