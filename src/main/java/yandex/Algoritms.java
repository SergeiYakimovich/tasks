package yandex;

import yandex.context1.YandexGraf;
import yandex.context2.BinaryTree;
import yandex.context2.SubMatrix;
import yandex.context4.FourSumNabor;
import yandex.graf.Deikstra1;
import yandex.graf.Deikstra2;
import yandex.graf.GraphBFS;
import yandex.graf.GraphDFS;
import yandex.hrtech.*;

public class Algoritms {
    // двоичные деревья
    BinaryTreeHeight binaryTreeHeight; // высота дерева
    BinaryTree  binaryTree;
    YandexGraf yandexGraf;

    GraphDFS graphDFS; // Обход в глубину (Depth-First Search, DFS)
    GraphBFS graphBFS; // Обход в ширину (Breadth-First Search, BFS)
    Deikstra2 graf; // Алгоритм Дейкстры нахождения оптимального пути в графе
    Deikstra1 deikstra; // Алгоритм Дейкстры нахождения оптимального пути в графе

    // префиксное суммы
    HappyNumberSegments_1 happyNumberSegments1;

    // 2 указателя
    HappyNumberSegments_2 happyNumberSegments2;

    // кратчайший путь через поиск в ширину (BFS)
    MinPath minPath;

    // двоичный поиск
    NearestNumber nearestNumber;

    // поиск заданного набора
    FourSumNabor fourSumNabor;

    // поиск подматрицы
    SubMatrix subMatrix;
}
