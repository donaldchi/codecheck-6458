#Shiritori framework
##構造
![alt text](http://ganbaruyo.net/images/structure.png "Logo Title Text 1")
##説明

1. Java socket通信を使ってしりとりゲームを作成

2. クラス説明

    2.1 Shitori: しりとりゲームの入り口クラス。審判クラスの作成、プレイヤーの待
ち、ゲームの開始などを行う。

    2.2 Referee: 審判クラス。プレイヤーの間で単語の渡し、ゲーム終了判断など行う
。

    2.3 PlayerThread: 審判クラスから生成され、該当プレイヤーから単語を受け取ったり、渡したりする。

    2.4 Player: プレイヤークラス。本ゲームのフレームワークで動作する戦略 (AI) を実装するために、このクラスを継承する必要がある。

    2.5 WordReceiver, WordSender : Playerクラスから生成され、それぞれ、相手の単語を審判から受け取ったり、プレイヤが言い出す単語を審判に送ったりなどのことを行う。

##実行
###ビルド
```
    ./remove.sh
    ./build.sh
```
###実行
```
    ./run.sh ./ai.sh ./ai.sh shiritori internet tail grep less telnet sed
```
### AIプログラムのオプション実行 
(-f オプションでdict.txtファイルからword dictionaryを読み取ることになる。)
```
    ./ai.sh -f
```

##備考
ゲームのルールには違反しないが、まだ、予測通りの結果が出ない場合がある。
