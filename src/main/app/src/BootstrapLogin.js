import React, { Component } from 'react'
import './BootstrapLogin.css'
import {
  Button, Form, FormGroup, Label, Input, FormText, Container
} from 'reactstrap';

// modified from https://bootsnipp.com/snippets/featured/login-cloud
class BootstrapLogin extends Component {

  render() {
    return (
      <Container >
        <div id="login-row" className="row justify-content-center align-items-center">
          <div id="login-column" className="col-md-8">
            <div className="box">
              <div className="shape1"></div>
              <div className="shape2"></div>
              <div className="shape3"></div>
              <div className="shape4"></div>
              <div className="shape5"></div>
              <div className="shape6"></div>
              <div className="shape7"></div>

              <div className="float">
                <Form className="text-left">
                  <FormGroup>
                    <Label for="username" className="text-white">Username:</Label>
                    <Input type="text" name="username" id="username" className="form-control" />
                  </FormGroup>
                  <FormGroup>
                    <Label for="password" className="text-white">Password:</Label>
                    <Input type="text" name="password" id="password" className="form-control" />
                  </FormGroup>
                  <FormGroup className="submit-container">
                    <Button type="submit" name="submit" className="btn-info btn-md">Submit</Button>
                  </FormGroup>
                </Form>
              </div>


            </div>
          </div>
        </div>
      </Container>


    )
  }
}

export default BootstrapLogin