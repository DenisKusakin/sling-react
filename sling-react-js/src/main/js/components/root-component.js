import React from 'react';

export default ({app, __initialState}) => (
    <html lang="en">
        <head>
          <meta charset="utf-8"/>
          <title>Sling-React Render</title>
        </head>

        <body>
            <div id="app">
                {app}
            </div>
            <script dangerouslySetInnerHTML={{__html: `window.__INITIAL_STATE = ${__initialState}`}}/>
            <script src="/etc/react-clientlibs/client.js"></script>
        </body>
    </html>
)