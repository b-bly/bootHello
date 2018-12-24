import React, { Component } from 'react';
import './App.css';
import { withRouter, Switch, Route } from 'react-router-dom'
import Home from './Home'
import Login from './Login'

class App extends Component {


  handleLogin = (loginObj) => {
    console.log(loginObj)
  }

  render() {
    return (
      <div className="App">
        <header>
          React App.js live
        </header>
        <a href="/parent/plain-login">Login</a>
        <Switch>
          <Route exact path="/"
            render={(props) => <Home />}>
          </Route>
          <Route path="/parent/plain-login"
            render={(props) => <Login onLogin={this.handleLogin.bind(this)} {...props} />}></Route>
          {/* <Route path="/signup" component={Signup}></Route> */}
        </Switch>
      </div>
    );
  }
}

export default withRouter(App);
