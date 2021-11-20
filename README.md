# BibTex2Word

This a program which transform the `bibtex` references to `word` references.

**Feel free for commiting to make it better**

The input format 
```tex
@inproceedings{shmyrin2018neighborhood,
	title={Neighborhood systems priority identification and randomized Kaczmarz algorithm},
	author={Shmyrin, AM and Mishachev, NM and Trofimov, EP},
	booktitle={2018 International Russian Automation Conference (RusAutoCon)},
	pages={1--4},
	year={2018},
	organization={IEEE}
}
```
will transform to 
```text
1. Shmyrin A. M., Mishachev N. M., Trofimov E. P. (2018) Neighborhood systems priority identification and " +
        "randomized Kaczmarz algorithm. Int. Russian Automation Conf. (RusAutoCon) (Sochi Russia), pp 1-4
```

Example:
![image info](example.png)
