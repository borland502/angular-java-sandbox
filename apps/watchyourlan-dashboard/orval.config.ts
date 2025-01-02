import { defineConfig } from 'orval';

export default defineConfig({
  watchyourlan: {
    output: {
      mode: 'tags-split',
      target: 'src/api/services',
      schemas: 'src/api/models',
      client: 'angular',
      mock: true,
      prettier: true,
      tsconfig: './tsconfig.app.json',
    },
    input: {
      target: 'http://127.0.0.1:8080/api/v1/api-docs',
    },
      hooks: {
        afterAllFilesWrite: "prettier --write",
      }
  },
});
