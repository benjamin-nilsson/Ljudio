import React, { useState, useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

import AuthService from "../services/auth.service";
import {Col, Row} from "antd";

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const Login = () => {
  let navigate = useNavigate();

  const form = useRef();
  const checkBtn = useRef();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeUsername = (e) => {
    const username = e.target.value;
    setUsername(username);
  };

  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };

  const handleLogin = (e) => {
    e.preventDefault();

    setMessage("");
    setLoading(true);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {
      AuthService.login(username, password).then(
        () => {
          navigate("/profile");
          window.location.reload();
        },
        () => {
          /* const resMessage =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString(); */
          //error message in type will give 403 error, instead I put a string value being wrong and reason

          setLoading(false);
          setMessage("Wrong password or UserName");
        }
      );
    } else {
      setLoading(false);
    }
  };

  return (
    <div className="col-md-12">
      <div className="col-md-12-card">
      <div className="column-login" align="center">
      <Row gutter={[24, 16]}>
        <Col span={12}>
           <u>SIGN IN</u>
        </Col>
        <Col span={12}>
          <a href="/register">  SIGN UP </a>
        </Col>
      </Row>
      </div>
      <div className="card card-container">

        <Form onSubmit={handleLogin} ref={form}>
          <div className="username-input">
            <Input
              type="text"
              className="form-control"
              name="username"
              value={username}
              onChange={onChangeUsername}
              validations={[required]}
              placeholder="username"
            />
          </div>
          <br/>
          <div className="password-input">
            <Input
              type="password"
              className="form-control"
              name="password"
              value={password}
              onChange={onChangePassword}
              validations={[required]}
              placeholder="password"
            />
          </div>
          <br />

          <div className="form-group">
            <button className="btn btn-primary btn-block" disabled={loading}>
              {loading && <span className="spinner-border spinner-border-sm" />}
              <span>Login</span>
            </button>
          </div>

          {message && (
            <div className="form-group">
              <div className="alert alert-danger" role="alert">
                {message}
              </div>
            </div>
          )}

          <CheckButton style={{ display: "none" }} ref={checkBtn} />
        </Form>
      </div>
      </div>
    </div>
  );
};

export default Login;
