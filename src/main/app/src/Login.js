import React, { Component } from 'react';
import { login } from './util/APIUtils';

const formStyle = {
  display: 'flex',
  flexFlow: 'column nowrap',
}

const formButtonStyle = {

}

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
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
    const loginRequest = { ...this.state }
    login(loginRequest)
      .then(response => {
        console.log('response: ');
        console.log(response)
        // redirects home
        this.props.onLogin();
      }).catch(error => {
        console.log(error);

      });

  }

  render() {
    return (
      <form onSubmit={this.handleSubmit} style={formStyle}>
        <label>
          Username:
          <input type="text" value={this.state.username} onChange={this.handleChange} name="username" />
        </label>
        <label>
          Password:
          <input type="password" value={this.state.password} onChange={this.handleChange} name="password" />
        </label>
        <div>
          <input type="submit" value="Submit" />
        </div>
      </form>
    );
  }
}

export default Login;