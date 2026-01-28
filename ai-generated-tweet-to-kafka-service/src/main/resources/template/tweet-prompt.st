Generate a JSON object with the following structure and constraints:

- The JSON must contain exactly 4 top-level fields:
  - createdAt
  - id
  - text
  - user

- The createdAt field must be a string formatted as:
  "EEE MMM dd HH:mm:ss zzz yyyy"
  Example: "Fri Oct 06 14:32:25 UTC 2023"

- The id field must be a Java long numeric value (no "L" suffix).

- The text field must be a string that includes one or more of the following keywords:
  {keywords}

- The user field must be a JSON object containing:
  - id: a Java long numeric value (no "L" suffix)

Output rules:
- Return ONLY the JSON text.
- Do NOT include markdown formatting.
- Do NOT include explanations or extra text.
- Do NOT use new lines; the JSON must be in a single line.
- Generate exactly ONE JSON object.

{format}
