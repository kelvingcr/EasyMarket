<h1 align="center">EasyMarket</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <br>
  <a href="https://wa.me/+5513988841491"><img alt="WhatsApp" src="https://img.shields.io/badge/WhatsApp-25D366?style=for-the-badge&logo=whatsapp&logoColor=white"/></a>
  <a href="https://www.linkedin.com/in/kelvingcr/"><img alt="Linkedin" src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"/></a>
  <a href="mailto:kelvingcr16@gmail.com"><img alt="Gmail" src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white"/></a>
</p>

<p align="center">  

⭐ Esse é um projeto para demonstrar meu conhecimento técnico no desenvolvimento Android nativo com Kotlin. Mais informações técnicas abaixo.

🛒 Aplicativo que facilita o gerencimento do carrinho de compras do supermercado, nele você pode criar produtos com caracteristicas, por exemplo, preço. E com ele você terá melhor gerenciamento de suas despesas.

</p>

</br>

<p float="left" align="center">
  <img alt="screenshot" width="100%" src="https://i.imgur.com/tEESM3h.png"/>
</p>

Nesse aplicativo também existe outras telas que não foram mencinadas na foto.

## Download

Faça o download da <a href="https://www.sendspace.com/file/gto186?raw=true">APK diretamente</a>. Você pode ver <a href="https://www.google.com/search?q=como+instalar+um+apk+no+android">aqui</a> como instalar uma APK no seu aparelho android.

## Tecnologias usadas e bibliotecas de código aberto

- Minimum SDK level 26
- [Linguagem Kotlin](https://kotlinlang.org/)

- Jetpack
  - Lifecycle: Observe os ciclos de vida do Android e manipule os estados da interface do usuário após as alterações do ciclo de vida.
  - ViewModel: Gerencia o detentor de dados relacionados à interface do usuário e o ciclo de vida. Permite que os dados sobrevivam a alterações de configuração, como rotações de tela.
  - ViewBinding: Liga os componentes do XML no Kotlin através de uma classe que garante segurança de tipo e outras vantagens.
  - Custom Views: View customizadas feitas do zero usando XML.
  - Navigation: Uma biblioteca que ajuda a gerenciar a navegação em um aplicativo. Ele oferece uma API intuitiva para criar e gerenciar fluxos de navegação entre diferentes telas e fragmentos.
  - LiveData: Um objeto observável que armazena um valor de dados e notifica automaticamente as observadoras quando há mudanças nos dados.
  - Preferences: A PreferenceScreen é uma interface de usuário do Android que exibe uma lista de preferências que um usuário pode modificar.
  
- Arquitetura
  - MVVM (View - ViewModel - Model)
  - Comunicação da ViewModel com a View através de LiveData
  - Comunicação da ViewModel com a Model através de Kotlin Flow
  - Repositories para abstração da comunidação com a camada de dados.
  
- Bibliotecas
  - [Hilt](https://github.com/googlecodelabs/android-hilt): Para injeção de dependencias automaticas.
  - [Firebase](https://github.com/firebase/): Para carregamento de imagens e cacheamento das mesmas.

## Arquitetura
**EasyMarket** utiliza a arquitetura MVVM e o padrão de Repositories, que segue as [recomendações oficiais do Google](https://developer.android.com/topic/architecture).
</br></br>
<img width="60%" src="https://i.imgur.com/336KI4q.png"/>
<br>

## Features

### Tela de Login e Registro

<p float="left" align="start">
  <img src="https://i.imgur.com/Dz1zZGh.png" width="25%"/>
  <img src="https://i.imgur.com/Pv6WmDf.png" width="25%"/>
</p>

Na imagem mostra que, é possivel o usuário fazer o login em uma conta já existente ou criar uma nova conta.

### Listagem de Compras | Visualizar uma compra existe | Criar uma nova compra.

<p float="left" align="start">
  <img width="25%" src="app/src/gifTelasSearch&New.gif"/>
  <img src="https://i.imgur.com/Tdz7AhK.png" width="50%"/>
  
</p>

Nessa tela exibi para o usúario sua lista de compras salvas. Cada usúario possue uma lista individual.
Também é possivel visualizar as informações de uma compra já existente.

# Licença
```xml

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

Google Play e o logótipo do Google Play são marcas comerciais da Google LLC.
