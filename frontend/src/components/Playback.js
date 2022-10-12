import React, {useEffect, useState} from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import "../css/Playback.css"
import {Button, Col, Row} from "antd";
import {
    DownOutlined,
    MoreOutlined,
    PlayCircleFilled,
    StepBackwardFilled,
    StepForwardFilled
} from "@ant-design/icons";


const Playback = ({id, src, title, artist}) => {
    const [content, setContent] = useState("");

    useEffect(() => {
        UserService.getUserBoard().then(
            (response) => {
                setContent(response.data);
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
                }
            }
        );
    }, []);

    return (
        <div className="playback-all">
            <img src={src}/>
        <div className="playback-header" >
            <Row justify="space-between">
            <Col span={4}>
            <Button
                className="header-button"
                type="back"
                href="/playlist"
                icon=<DownOutlined style={{ fontSize: "175%" }}/>
            />
            </Col>
                <Col span={4}>
                <div className="playlist-tag">
                    playlist
                </div>
                <div className="playlist-image">
                    <b> party </b>
                </div>
                </Col>
            <Col span={4}>
            <Button
                className="header-button"
                type="back"
                href=""
                icon=<MoreOutlined style={{ fontSize: "175%" }}/>
            />
            </Col>
            </Row>
        </div>
        <div className="artist-name">
            <o>{artist}</o>
        </div>
            <div className="artist-song">
                <o> {title}</o>
            </div>
            <div className="playback-footer" >
            <div className="divider">
                <Button
                    className="footer-button"
                    type="back"
                    href=""
                    icon={<StepBackwardFilled  style={{ fontSize: "175%" }} />}
                />
                <Button
                    className="footer-button"
                    type="play"
                    href=""
                    icon={<PlayCircleFilled style={{ fontSize: "175%" }} />}
                />
                <Button
                    className="footer-button"
                    type="forward"
                    href=""
                    icon={<StepForwardFilled  style={{ fontSize: "175%" }} />}
                />
            </div>
            </div>

        </div>

    );
};

export default Playback;