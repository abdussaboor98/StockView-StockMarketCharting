import { Component, OnInit, Input, OnChanges, SimpleChange } from '@angular/core';

@Component({
  selector: 'app-password-strength',
  templateUrl: './password-strength.component.html',
  styleUrls: ['./password-strength.component.css']
})
export class PasswordStrengthComponent implements OnInit, OnChanges {

  bar0: string;
  bar1: string;
  bar2: string;
  bar3: string;
  private colors = ['darkred', 'orangered', 'orange', 'yellowgreen'];

  @Input() public passwordToCheck: string;
  
  constructor() { }

  ngOnInit() {
  }

  ngOnChanges(changes: { [propName: string]: SimpleChange }): void {
    const password = changes.passwordToCheck.currentValue;
    this.setBarColors(4, '#DDD');
    if (password) {
      const c = this.getColor(this.checkStrength(password));
      this.setBarColors(c.index, c.color);
    }
  }
  
  private getColor(s) {
    let index = 0;
    if (s === 10) {
      index = 0;
    } else if (s === 20) {
      index = 1;
    } else if (s === 30) {
      index = 2;
    } else if (s === 40) {
      index = 3;
    } else {
      index = 4;
    }
    return {
      index: index + 1,
      color: this.colors[index]
    };
  }
  
  private setBarColors(count, col) {
    for (let n = 0; n < count; n++) {
      this['bar' + n] = col;
    }
  }

  checkStrength(p) {
    // 1
    let strength = 0;
  
    // 2
    const regex = /[$-/:-?{-~!"^_@`\[\]]/g;
    const lowerLetters = /[a-z]+/.test(p);
    const upperLetters = /[A-Z]+/.test(p);
    const numbers = /[0-9]+/.test(p);
    const symbols = regex.test(p);
  
    // 3
    const flags = [lowerLetters, upperLetters, numbers, symbols];
  
    // 4
    let passedMatches = 0;
    for (const flag of flags) {
      passedMatches += flag === true ? 1 : 0;
    }
  
    // 5
    strength += 2 * p.length + ((p.length >= 10) ? 1 : 0);
    strength += passedMatches * 10;
  
    // 6
    strength = (p.length <= 6) ? Math.min(strength, 10) : strength;
  
    // 7
    strength = (passedMatches === 1) ? Math.min(strength, 10) : strength;
    strength = (passedMatches === 2) ? Math.min(strength, 20) : strength;
    strength = (passedMatches === 3) ? Math.min(strength, 30) : strength;
    strength = (passedMatches === 4) ? Math.min(strength, 40) : strength;
  
    return strength;
  }

}
