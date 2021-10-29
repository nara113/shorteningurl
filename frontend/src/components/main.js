import React, {useState} from "react";
import axios from "axios";
import "./Main.css"

const Main = () => {
    const [url, setUrl] = useState(null);
    const [shortUrl, setShortUrl] = useState(null);

    const getShortUrl = (event) => {
        event.preventDefault();

        const formData = new FormData();
        formData.append('originalUrl', url);

        axios.post('url', formData)
            .then((response) => {
                setShortUrl(response.data);
            }).catch(() => {
            alert('url 형식이 올바르지 않습니다.');
        })
    }

    return (
        <>
            <form className="border py-3" onSubmit={getShortUrl}>
                <div className="search">
                    <input type="text"
                           placeholder="https://www.naver.com/"
                           onChange={event => setUrl(event.target.value)}
                    />
                </div>
                <div className="button">
                    <button>검색</button>
                </div>
            </form>
            {shortUrl &&
            <div> 변환된 URL : {shortUrl}</div>
            }
        </>
    )
}
export default Main