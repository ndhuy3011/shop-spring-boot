from flask import Flask

app = Flask(__name__)

@app.route("/")
def hello_world():
    import ollama

    response = ollama.chat(model='deepseek-r1:7b', messages=[
    {
        'role': 'user',
        'content': 'Why is the sky blue?',
    },
    ])

    return response['message']['content']