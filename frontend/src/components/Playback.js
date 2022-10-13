import React, {useEffect, useState} from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import "../css/Playback.css"
import {Button, Col, Row} from "antd";
import {nextSong, prevSong} from "../client";
import {
    DownOutlined,
    MoreOutlined,
    PlayCircleFilled,
    StepBackwardFilled,
    StepForwardFilled
} from "@ant-design/icons";
import fetch from "unfetch";


const Playback = ({id, source, titles, artists}) => {
    const [content, setContent] = useState("");
    const [next, setNext] = useState({});
    const [prev, setPrev] = useState({});
    const [title, setTitle] = useState(titles);
    const [artist, nextArtist] = useState(artists)
    const [src, setSrc] = useState(source)

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

    const nextSong = () => {
        setNext(nextSong)
    }

    return (
        <div className="playback-all">
            <img src={src} alt=""/>
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
            <Button
                className="header-button"
                type="back"
                href="javascript:history.back()"
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