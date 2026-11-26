import os
from urllib.request import Request, urlopen
from urllib.error import HTTPError, URLError
from flask import Flask, Response, render_template, request

app = Flask(__name__)

API_BASE_URL = os.getenv("GRADEBOOK_API_URL", "http://localhost:8080").rstrip("/")


@app.route("/")
def index():
    return render_template("index.html")


@app.route("/api/<path:subpath>", methods=["GET", "POST", "PUT", "DELETE", "PATCH"])
def proxy_api(subpath):
    target_url = f"{API_BASE_URL}/{subpath}"
    if request.query_string:
        target_url = f"{target_url}?{request.query_string.decode('utf-8')}"

    body = request.get_data() if request.method in ("POST", "PUT", "DELETE", "PATCH") else None
    headers = {}
    if "Content-Type" in request.headers:
        headers["Content-Type"] = request.headers["Content-Type"]

    req = Request(target_url, data=body, headers=headers, method=request.method)
    try:
        with urlopen(req, timeout=10) as resp:
            return Response(
                resp.read(),
                status=resp.status,
                content_type=resp.headers.get("Content-Type", "application/json"),
            )
    except HTTPError as err:
        return Response(
            err.read(),
            status=err.code,
            content_type=err.headers.get("Content-Type", "application/json"),
        )
    except URLError as err:
        return Response(
            f'{{"message": "Backend unavailable: {err.reason}"}}',
            status=502,
            content_type="application/json",
        )


if __name__ == "__main__":
    app.run(host="127.0.0.1", port=5000, debug=False)