# LoopbackQuery
Note: LoopbackQuery is still a work in progress.

Example:
```
String order = "dateModified ASC";
String[] fields = {"transactions", "houses", "devices"};
Query query = new Query.Builder().include(
        new Include.Builder()
        .order(order).scope(
              new Scope.Builder().fields(fields)))
        .build();
```

Output with ```toString()```:
```
{
  "include": {
    "scope": {
      "fields": [
        "transactions",
        "houses",
        "devices"
      ]
    },
    "order": "dateModified ASC"
  }
}
```
