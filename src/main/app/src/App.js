import React, { Component } from 'react';
import './App.css';
import { withRouter, Switch, Route } from 'react-router-dom'
// Request methods
import { getCurrentUser } from './util/APIUtils'

// Components
import Home from './Home'
import Login from './Login'
import BootstrapLogin from './BootstrapLogin'

class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      currentUser: null,
      isAuthenticated: false,
      isLoading: false,
    }
    this.loadCurrentUser = this.loadCurrentUser.bind(this);
  }

  loadCurrentUser() {
    this.setState({
      isLoading: true
    });
    getCurrentUser()
      .then(response => {
        console.log(response)
        this.setState({
          currentUser: response,
          isAuthenticated: true,
          isLoading: false
        });
      }).catch(error => {
        this.setState({
          isLoading: false
        });
      });
  }

  componentDidMount() {
    this.loadCurrentUser();
  }

  handleLogin = (loginObj) => {
    console.log(loginObj)

    this.props.history.push('/')
  }

  handleLogout(redirectTo = "/", notificationType = "success", description = "You're successfully logged out.") {
    // localStorage.removeItem(ACCESS_TOKEN);

    this.setState({
      currentUser: null,
      isAuthenticated: false
    });

    this.props.history.push(redirectTo);
  }

  render() {
    return (
      <div className="App">
        <header>
          React Hello Boot
        </header>
        <div>Current user: </div>
        <a href="/client/login">Custom Login</a>
        <a href="/login">Boot Login</a>
        <form action="/logout" method="post">
          <input
            type="submit"
            value="Sign Out"
          />
        </form>

        <Switch>
          <Route exact path="/client/forbidden"
            render={(props) => <Home />}>
          </Route>
          <Route path="/client/login"
            render={(props) => <Login onLogin={this.handleLogin.bind(this)} {...props} />}></Route>
          {/* <Route path="/signup" component={Signup}></Route> */}
        </Switch>
      </div>
    );
  }
}

export default withRouter(App);
