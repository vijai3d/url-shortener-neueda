import React, { Component } from 'react';
import Url from './Url.jsx';
import 'materialize-css';
import 'materialize-css/dist/css/materialize.min.css';

class App extends Component {
    render() {
        return <div className="container">
            <Url></Url>
        </div>
    }
}

export default App;
