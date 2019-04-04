import React, { Component } from 'react';

class UrlLongener extends Component {

    constructor() {
        super();
        this.getLongUrl = this.getLongUrl.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.state = {
            longUrl: '',
            generatedId: '',
        };
    }

    handleChange(event) {
        this.setState({generatedId: event.target.value});
    }

    getLongUrl(event) {
        event.preventDefault();

        fetch('http://localhost:8085/rest/v1/url/get', {
            method: 'POST',
            body: this.state.generatedId,
        })
            .then(response => response.json())
            .then(result => this.setState({longUrl: result.url}))
            .catch(e => console.log(e));
    }

    render() {
        return (
            <div className="col s6 m2">
                <h3 className="grey-text text-darken-2">Get long url</h3>
                <form onSubmit={this.getLongUrl}>
                    <div className="input-field col s6">
                        <input id="short_url" name="url" type="text" className="validate" onChange={this.handleChange}/>
                        <label htmlFor="short_url">Short URL</label>
                    </div>

                    <button className="btn waves-effect waves-light" name="action">Submit
                        <i className="material-icons right">send</i>
                    </button>
                </form>
                <p>Long url is: <strong>{this.state.longUrl}</strong></p>
            </div>
        );
    }
}

export default UrlLongener;
