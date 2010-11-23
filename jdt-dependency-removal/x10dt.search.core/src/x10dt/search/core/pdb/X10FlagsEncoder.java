/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation.                                         *
 * All rights reserved. This program and the accompanying materials            *
 * are made available under the terms of the Eclipse Public License v1.0       *
 * which accompanies this distribution, and is available at                    *
 * http://www.eclipse.org/legal/epl-v10.html                                   *
 *******************************************************************************/
package x10dt.search.core.pdb;

import polyglot.types.Flags;
import x10.types.X10Flags;

/**
 * Responsible for encoding relevant X10 flags for search indexing with a simple integer.
 * 
 * @author egeay
 */
public final class X10FlagsEncoder {
  
  /**
   * The list of X10 flags.
   * 
   * @author egeay
   */
  public enum X10 {
    
    /**
     * <code>public</code>.
     */
    PUBLIC(1),
    
    /**
     * <code>private</code>.
     */
    PRIVATE(1 << 1),
    
    /**
     * <code>protected</code>.
     */
    PROTECTED(1 << 2),
    
    /**
     * <code>static</code>.
     */
    STATIC(1 << 3),
    
    /**
     * <code>final</code>.
     */
    FINAL(1 << 4),
        
    /**
     * <code>native</code>.
     */
    NATIVE(1 << 5),
    
    /**
     * <code>interface</code>.
     */
    INTERFACE(1 << 6),
    
    /**
     * <code>abstract</code>.
     */
    ABSTRACT(1 << 7),
    
    /**
     * <code>extern</code>.
     */
    EXTERN(1 << 8),
    
    /**
     * <code>atomic</code>.
     */
    ATOMIC(1 << 9),
    
    /**
     * <code>global</code>.
     */
    GLOBAL(1 << 10),
    
    /**
     * <code>pinned</code>.
     */
    PINNED(1 << 11),
    
    /**
     * <code>safe</code>.
     */
    SAFE(1 << 12),
    
    /**
     * <code>nonblocking</code>.
     */
    NONBLOCKING(1 << 13),
    
    /**
     * <code>sequential</code>.
     */
    SEQUENTIAL(1 << 14),
    
    /**
     * <code>property</code>.
     */
    PROPERTY(1 << 15);
    
    // --- Public services
    
    /**
     * Returns the unique code that identifies the list of X10 flags modeled.
     * 
     * @return A natural number.
     */
    public int getCode() {
      return this.fCode;
    }
   
    // --- Private code
    
    private X10(final int code) {
      this.fCode = code;
    }
    
    private final int fCode;
    
  }
  
  /**
   * Initializes the unique code representing the X10 flags.
   * 
   * @param flags The X10 flags to consider.
   */
  public X10FlagsEncoder(final Flags flags) {
    int code = 0;
    if (flags.isPublic()) {
      code |= X10.PUBLIC.getCode();
    }
    if (flags.isPrivate()) {
      code |= X10.PRIVATE.getCode();
    }
    if (flags.isProtected()) {
      code |= X10.PROTECTED.getCode();
    }
    if (flags.isStatic()) {
      code |= X10.STATIC.getCode();
    }
    if (flags.isFinal()) {
      code |= X10.FINAL.getCode();
    }
    if (flags.isNative()) {
      code |= X10.NATIVE.getCode();
    }
    if (flags.isInterface()) {
      code |= X10.INTERFACE.getCode();
    }
    if (flags.isAbstract()) {
      code |= X10.ABSTRACT.getCode();
    }
    if (flags instanceof X10Flags) {
      final X10Flags x10Flags = (X10Flags) flags;
      if (x10Flags.isExtern()) {
        code |= X10.EXTERN.getCode();
      }
      if (x10Flags.isAtomic()) {
        code |= X10.ATOMIC.getCode();
      }
      if (x10Flags.isGlobal()) {
        code |= X10.GLOBAL.getCode();
      }
      if (x10Flags.isPinned()) {
        code |= X10.PINNED.getCode();
      }
      if (x10Flags.isSafe()) {
        code |= X10.SAFE.getCode();
      }
      if (x10Flags.isNonBlocking()) {
        code |= X10.NONBLOCKING.getCode();
      }
      if (x10Flags.isSequential()) {
        code |= X10.SEQUENTIAL.getCode();
      }
      if (x10Flags.isProperty()) {
        code |= X10.PROPERTY.getCode();
      }
    }
    this.fCodeRep = code;
  }
  
  // --- Public services
  
  /**
   * Returns the unique code representing the list of X10 flags encapsulated.
   * 
   * @return A natural number.
   */
  public int getCode() {
    return this.fCodeRep;
  }
  
  // --- Fields
  
  private final int fCodeRep;

}
