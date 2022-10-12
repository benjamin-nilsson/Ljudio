import React from "react";
import AuthService from "../services/auth.service";
import {useEffect, useState} from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import {useNavigate} from "react-router-dom";
import "../css/profile.css"
import {UserOutlined} from "@ant-design/icons";

const Profile = () => {
    let navigate = useNavigate();
    const [content, setContent] = useState("");
    const user = AuthService.getCurrentUser();

    useEffect(() => {
        UserService.getUserBoard().then(
            (response) => {
                setContent(<div className="col-md-12">
                    <div className="col-md-12-card">
                        <div className="card card-container">
                        <div className="profile-card form-control">
                        <header className="jumbotron">
                    <h3>
                        <strong>{user.username}</strong> Profile <UserOutlined />
                    </h3>
                </header>
                <p>
                    <strong>Email:</strong> {user.email}
                </p>
                <button>
                <a href="/login" className="nav-link" onClick={logOut}>
                    LogOut
                </a></button></div>
                    </div>
                    </div>
                    </div>
                    );
            },
            (error) => {
                const _content =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setContent(_content);

                if (error.response && error.response.status === 401) {
                    EventBus.dispatch("logout");
                    navigate("/")
                    window.location.reload();
                }
            }
        );
    }, []);

    const logOut = () => {
        AuthService.logout();
        user.setCurrentUser(undefined);
    };

    return (
        <div className="container">
            {content}
        </div>
    );
};

export default Profile;