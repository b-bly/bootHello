import React, { Component } from 'react';

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: null,
      password: null,
    }
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this)
  }

  handleChange(event) {
    this.setState({
        [event.target.name]: event.target.value
    })
}

  handleSubmit(event) {
    event.preventDefault();
    this.props.onLogin(this.state);
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label>
          Username:
          <input type="text" value={this.state.value} onChange={this.handleChange} name="username"/>
        </label>
        <label>
          Password:
          <input type="password" value={this.state.value} onChange={this.handleChange} name="password"/>
        </label>
        <input type="submit" value="Submit" />
      </form>
    );
  }
}

export default Login;