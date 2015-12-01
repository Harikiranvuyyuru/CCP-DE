#Avro

## Compiling Java code from Avro schema

#### user.avsc

```json
	{"namespace": "example.avro",
	  "type": "record",
	  "name": "User",
 	  "fields": [
	      {"name": "name", "type": "string"},
 	      {"name": "favorite_number",  "type": ["int", "null"]},
	      {"name": "favorite_color", "type": ["string", "null"]}
	  ]
	}
```

#### Compiling Java code from schema

```sh
  java -jar avro-tools-1.7.7.jar compile schema user.avsc .
```

#### Installing `avro` Python library

```sh
  pip install avro
```
