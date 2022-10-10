import "./../../css/Search.css";
import React, { useEffect, useState, useRef } from "react";
//import Search from "antd/lib/transfer/search";
import { Input } from "antd";
import { search } from "../../client";
import Card from "./Card";

const { Search } = Input;

const Find = () => {
  const [query, setQuery] = useState("");
  const [searchResult, setSearchResult] = useState(null);
  const isFirstRender = useRef(true);

  const fetchSearchResult = async () => {
    const res = await search(query, query);
    const data = await res.json();

    return data;
  };

  useEffect(() => {
    if (isFirstRender.current) isFirstRender.current = false;
    else {
      console.log(query);
      const getSearchResult = async () => {
        const respons = await fetchSearchResult(); // Leave artist crossed out for now
        setSearchResult(respons);
      };

      getSearchResult();
    }
  }, [query]);

  const searchFunction = (
    <Search
      placeholder="What do you want to listen to?"
      onSearch={(value) => setQuery(value)}
      enterButton
    />
  );

  return (
    <div style={{ margin: "20px 10px" }}>
      <h1 style={{ color: "white" }}>Search</h1>
      <div className="search">
        <div className="box">{searchFunction}</div>
      </div>
      <h2 style={{ color: "white", paddingTop: "1rem" }}>Results</h2>
      <div>
        {searchResult != null &&
          searchResult.map((result, index) => (
            <Card
              on={true}
              key={index}
              id={result.track.id}
              src={result.track.image}
              title={result.track.songName}
              artist={result.track.artistName}
            />
          ))}
      </div>
    </div>
  );
};

export default Find;
