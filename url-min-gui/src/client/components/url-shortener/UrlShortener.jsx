import React, { Component } from 'react';

class UrlShortener extends Component {

    constructor() {
        super();
        this.getShortUrl = this.getShortUrl.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.state = {
            longUrl: '',
            generatedId: '',
        };
    }

    handleChange(event) {
        this.setState({longUrl: event.target.value});
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


    render() {
        return (
            <div className="col s6 m2">
                <h3 className="grey-text text-darken-2">Create short url</h3>
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
            </div>
        );
    }
}

export default UrlShortener;
