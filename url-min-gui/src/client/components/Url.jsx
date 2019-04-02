import React, { Component } from 'react';
import axios from 'axios';

class Url extends Component {

    constructor() {
        super();
        this.getShortUrl = this.getShortUrl.bind(this);
        this.getLongUrl = this.getLongUrl.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleChange2 = this.handleChange2.bind(this);
        this.state = {
            longUrl: '',
            generatedId: '',
            shortUrl: '',
            fetchedLongUrl: ''
        };
    }

    handleChange(event) {
        this.setState({longUrl: event.target.value});
    }

    handleChange2(event) {
        this.setState({shortUrl: event.target.value});
    }

    getShortUrl(event) {
        event.preventDefault();

        fetch('http://localhost:8085/rest/v1/url/create', {
            method: 'POST',
            body: this.state.longUrl,
        })
            .then(response => response.json())
            .then(result => this.setState({generatedId: result.id}))
            .catch(e => console.log(e));
    }

    getLongUrl(event) {
        event.preventDefault();

        fetch('http://localhost:8085/rest/v1/url/get', {
            method: 'POST',
            body: this.state.shortUrl,
        })
            .then(response => response.json())
            .then(result => this.setState({fetchedLongUrl: result.url}))
            .catch(e => console.log(e));
    }

    render() {
        return (
            <div className="col s6 m2">
                <h3>Create short url</h3>
                <form onSubmit={this.getShortUrl}>
                    <div className="input-field col s6">
                        <input id="long_url" name="url" type="text"  className="validate" onChange={this.handleChange}/>
                            <label htmlFor="long_url">Long URL</label>
                    </div>

                    <button className="btn waves-effect waves-light" name="action">Submit
                        <i className="material-icons right">send</i>
                    </button>
                </form>
                <p>Sort url is: <strong>{this.state.generatedId}</strong></p>
                <br/>
                <h3>Get long url</h3>
                <form onSubmit={this.getLongUrl}>
                    <div className="input-field col s6">
                        <input id="short_url" name="url" type="text" className="validate" onChange={this.handleChange2}/>
                        <label htmlFor="short_url">Short URL</label>
                    </div>

                    <button className="btn waves-effect waves-light" name="action">Submit
                        <i className="material-icons right">send</i>
                    </button>
                </form>
                <p>Long url is: <strong>{this.state.fetchedLongUrl}</strong></p>
            </div>
        );
    }
}

export default Url;
