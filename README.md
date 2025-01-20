# YouTube API Integration em Java

Este projeto implementa a integração com a API do YouTube utilizando Java, permitindo a autenticação, envio de vídeos e outras interações com a plataforma.

## Requisitos

- Java 11 ou superior
- Maven ou Gradle
- Credenciais da API do YouTube (Client ID e Client Secret)
- Biblioteca Google API Client para Java

## Configuração

1. Crie um projeto na [Google Cloud Console](https://console.cloud.google.com/).
2. Ative a YouTube Data API v3.
3. Gere credenciais de autenticação (OAuth 2.0 Client ID e Secret).
4. Baixe o arquivo `credentials.json` e coloque-o no diretório `src/main/resources/client_secrets.json` do projeto.

## Instalação

Adicione as dependências no `pom.xml` caso utilize Maven:

```xml
<dependency>
    <groupId>com.google.api-client</groupId>
    <artifactId>google-api-client</artifactId>
    <version>1.32.1</version>
</dependency>
<dependency>
    <groupId>com.google.oauth-client</groupId>
    <artifactId>google-oauth-client-java6</artifactId>
    <version>1.32.1</version>
</dependency>
<dependency>
    <groupId>com.google.apis</groupId>
    <artifactId>google-api-services-youtube</artifactId>
    <version>v3-rev222-1.25.0</version>
</dependency>
```

Se estiver usando Gradle, adicione ao `build.gradle`:

```gradle
dependencies {
    implementation 'com.google.api-client:google-api-client:1.32.1'
    implementation 'com.google.oauth-client:google-oauth-client-java6:1.32.1'
    implementation 'com.google.apis:google-api-services-youtube:v3-rev222-1.25.0'
}
```

## Contribuição

Sinta-se à vontade para contribuir enviando pull requests e relatar problemas na aba de issues.

## Licença

Este projeto está sob a licença MIT. Consulte o arquivo `LICENSE` para mais informações.

