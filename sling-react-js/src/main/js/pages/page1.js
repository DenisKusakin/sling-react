import React from 'react'
import Page1 from './../content/page1.json'
import components from './../components'
import dialogs from './../components/dialog'
import AuthorApp from './../framework/AuthorApp'

export default () => <AuthorApp content={Page1} components={components} dialogs={dialogs}/>