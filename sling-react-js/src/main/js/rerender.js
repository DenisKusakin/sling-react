import ReactDOM from 'react-dom'
import React from 'react'
import createReactElement from './create-react-element.js'
import axios from 'axios'

window.rerender = () => axios.get(window.location.href.replace(".html", ".json"))
  .then(({data: {app}}) => {
      ReactDOM.render(createReactElement(app), document.getElementById('app'))
  })
  .catch(function (error) {
    console.log(error);
  });