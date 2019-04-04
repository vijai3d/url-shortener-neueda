import React from 'react';
import { render } from 'react-dom';
import { Route, Link, BrowserRouter as Router } from 'react-router-dom'
import App from './components/App.jsx';

import styles from './scss/application.scss';

render((
    <Router>
        <div>
            <Route path="/" component={App} />
        </div>
    </Router>
), document.getElementById('root'));
