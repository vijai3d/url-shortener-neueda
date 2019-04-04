import React, { Component } from 'react';
import UrlShortenerForm from './url-shortener/UrlShortener.jsx';
import UrlLongenerForm from './url-shortener/UrlLongener.jsx';
import 'materialize-css';
import 'materialize-css/dist/css/materialize.min.css';

class App extends Component {
    render() {
        return <div className="container">
            <div className="card-panel">
                <UrlShortenerForm/>
                <UrlLongenerForm/>
            </div>

        </div>
    }
}

export default App;
