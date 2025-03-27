import ollama

def generate_text(prompt,model="deepseek-r1:7b"):
  response = ollama.chat(model=model, messages=[
    {
      'role': 'user',
      'content': 'Why is the sky blue?',
    },
  ])
  print(response['message']['content'])