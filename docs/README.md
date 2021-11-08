# 🎥 Moovie

![GitHub repo size](https://img.shields.io/github/repo-size/iuricode/README-template?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/iuricode/README-template?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/iuricode/README-template?style=for-the-badge)
![Bitbucket open issues](https://img.shields.io/bitbucket/issues/iuricode/README-template?style=for-the-badge)
![Bitbucket open pull requests](https://img.shields.io/bitbucket/pr-raw/iuricode/README-template?style=for-the-badge)

🚧 EM CONSTRUÇÃO 🚧

<img src="preview.gif" alt="App preview">

O app ainda está em desenvolvimento e tem como intuito colocar em prática algumas ferramentas e
técnicas que estão em alta no mundo Android, além de melhorar algumas skills. A ideia é que usemos
os seguintes stack de componentes:

- [X] Dynamic features
- [X] Motion layout
- [X] Navigation
- [X] Coroutines
- [X] ViewModel
- [X] Single Activity
- [X] Dagger 2 (E futuramente migrar para o HILT. Escolhi o dagger por ser mais complexo de
  configurar, forçando o aprendizado)
- [X] Mockk
- [X] JUnit
- [X] Jenkins
- [ ] Espresso
- [ ] Gradle KTS
- [ ] Compose

### 🚀 Como está estruturado o projeto?

<img src="modules.jpg" alt="Modules organization">

- Módulo app: possui a estrutura de navegação e a parte central da injeção de dependencias.

- Módulo core: centraliza componentes cruciais para toda a aplicação, como configurações de rede,
  banco, injeção de depêndencias, etc.

- Módulo commons: possui estruturas comuns para a aplicação, como componentes, definições base,
  estilos, etc.

- Módulos de libraries: são módulos que podem ser extraídos e usados por outras aplicações, são
  interessantes pois fomentam a criação de bibliotecas para a comunidade.

- Módulos de features: são módulos dividos em duas partes. O modulo privado que contém as telas,
  regras de exibição e comunicação com banco e api. Já o módulo público contém os models, interfaces
  e o use case que poderão ser providos para outras partes do app.

### ☕ Pontos importantes:

O grafo das depêndencias do gradle está sendo otimizado, por ser algo novo, conforme o projeto
evolui algumas adequações precisam ser feitas, porém hoje o projeto está seguindo o seguinte padrão.
Observe que os módulos publicos das features precisaram ser incorporados no :app como APIs, tal ação
é necessária para que não tenhamos dependencias circulares no projeto, inviabilizando a sua
compilação.

<img src="communication.jpg" alt="Modules connection">

## 😄 Seja um dos contribuidores<br>

Quer fazer parte desse projeto? Abra uma PR com sua contribuição.

## 📝 Licença

Esse projeto está sob licença. Veja o arquivo [LICENÇA](LICENSE.md) para mais detalhes.

[⬆ Voltar ao topo](#nome-do-projeto)<br>

