#ifndef __X10_UTIL_TEAM_H
#define __X10_UTIL_TEAM_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
class FinishState;
} } 
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace lang { 
class RuntimeException;
} } 
namespace x10 { namespace compiler { 
class Finalization;
} } 
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace lang { 
class Byte;
} } 
#include <x10/lang/Byte.struct_h>
namespace x10 { namespace lang { 
class UByte;
} } 
#include <x10/lang/UByte.struct_h>
namespace x10 { namespace lang { 
class Short;
} } 
#include <x10/lang/Short.struct_h>
namespace x10 { namespace lang { 
class UShort;
} } 
#include <x10/lang/UShort.struct_h>
namespace x10 { namespace lang { 
class UInt;
} } 
#include <x10/lang/UInt.struct_h>
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace lang { 
class ULong;
} } 
#include <x10/lang/ULong.struct_h>
namespace x10 { namespace lang { 
class Float;
} } 
#include <x10/lang/Float.struct_h>
namespace x10 { namespace lang { 
class Double;
} } 
#include <x10/lang/Double.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class IndexedMemoryChunk;
} } 
#include <x10/util/IndexedMemoryChunk.struct_h>
namespace x10 { namespace util { 
class Team__DoubleIdx;
} } 
#include <x10/util/Team__DoubleIdx.struct_h>
namespace x10 { namespace lang { 
class IllegalArgumentException;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace compiler { 
class NonEscaping;
} } 
#include <x10/util/Team.struct_h>

namespace x10 { namespace util { 

class Team_methods  {
    public:
    static void _instance_init(x10::util::Team& this_);
    
    static x10::util::Team FMGL(WORLD);
    
    static void FMGL(WORLD__do_init)();
    static void FMGL(WORLD__init)();
    static volatile x10aux::status FMGL(WORLD__status);
    static inline x10::util::Team FMGL(WORLD__get)() {
        if (FMGL(WORLD__status) != x10aux::INITIALIZED) {
            FMGL(WORLD__init)();
        }
        return x10::util::Team_methods::FMGL(WORLD);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(WORLD__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(WORLD__id);
    
    static x10_int id(x10::util::Team this_) {
        
        //#line 40 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return this_->FMGL(id);
        
    }
    static void _constructor(x10::util::Team& this_, x10_int id) {
        {
         
        }
        
        //#line 42 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
        this_->FMGL(id) = id;
        
    }
    inline static x10::util::Team _make(x10_int id) {
        x10::util::Team this_; 
        _constructor(this_, id);
        return this_;
    }
    
    static void _constructor(x10::util::Team& this_, x10aux::ref<x10::lang::Rail<x10::lang::Place > > places);
    
    inline static x10::util::Team _make(x10aux::ref<x10::lang::Rail<x10::lang::Place > > places)
    {
        x10::util::Team this_; 
        _constructor(this_, places);
        return this_;
    }
    
    static void _constructor(
      x10::util::Team& this_, x10aux::ref<x10::array::Array<x10::lang::Place> > places);
    
    inline static x10::util::Team _make(
             x10aux::ref<x10::array::Array<x10::lang::Place> > places)
    {
        x10::util::Team this_; 
        _constructor(this_, places);
        return this_;
    }
    
    static void _constructor(
      x10::util::Team& this_, x10::util::IndexedMemoryChunk<x10::lang::Place > places,
      x10_int count);
    
    inline static x10::util::Team _make(
             x10::util::IndexedMemoryChunk<x10::lang::Place > places,
             x10_int count)
    {
        x10::util::Team this_; 
        _constructor(this_, places,
        count);
        return this_;
    }
    
    static void
      nativeMake(
      x10::util::IndexedMemoryChunk<x10::lang::Place > places,
      x10_int count,
      x10::util::IndexedMemoryChunk<x10_int > result);
    static x10_int
      size(
      x10::util::Team this_) {
        
        //#line 68 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return x10::util::Team_methods::nativeSize(
                 this_->
                   FMGL(id));
        
    }
    static x10_int
      nativeSize(
      x10_int id);
    static void
      barrier(
      x10::util::Team this_, x10_int role);
    static void
      nativeBarrier(
      x10_int id,
      x10_int role);
    template<class FMGL(T)>
    static void
      scatter(
      x10::util::Team this_, x10_int role,
      x10_int root,
      x10aux::ref<x10::lang::Rail<FMGL(T) > > src,
      x10_int src_off,
      x10aux::ref<x10::lang::Rail<FMGL(T) > > dst,
      x10_int dst_off,
      x10_int count);
    template<class FMGL(T)>
    static void
      scatter(
      x10::util::Team this_, x10_int role,
      x10_int root,
      x10aux::ref<x10::array::Array<FMGL(T)> > src,
      x10_int src_off,
      x10aux::ref<x10::array::Array<FMGL(T)> > dst,
      x10_int dst_off,
      x10_int count);
    template<class FMGL(T)>
    static void
      nativeScatter(
      x10_int id,
      x10_int role,
      x10_int root,
      x10::util::IndexedMemoryChunk<FMGL(T) > src,
      x10_int src_off,
      x10::util::IndexedMemoryChunk<FMGL(T) > dst,
      x10_int dst_off,
      x10_int count);
    template<class FMGL(T)>
    static void
      bcast(
      x10::util::Team this_, x10_int role,
      x10_int root,
      x10aux::ref<x10::lang::Rail<FMGL(T) > > src,
      x10_int src_off,
      x10aux::ref<x10::lang::Rail<FMGL(T) > > dst,
      x10_int dst_off,
      x10_int count);
    template<class FMGL(T)>
    static void
      bcast(
      x10::util::Team this_, x10_int role,
      x10_int root,
      x10aux::ref<x10::array::Array<FMGL(T)> > src,
      x10_int src_off,
      x10aux::ref<x10::array::Array<FMGL(T)> > dst,
      x10_int dst_off,
      x10_int count);
    template<class FMGL(T)>
    static void
      nativeBcast(
      x10_int id,
      x10_int role,
      x10_int root,
      x10::util::IndexedMemoryChunk<FMGL(T) > src,
      x10_int src_off,
      x10::util::IndexedMemoryChunk<FMGL(T) > dst,
      x10_int dst_off,
      x10_int count);
    template<class FMGL(T)>
    static void
      alltoall(
      x10::util::Team this_, x10_int role,
      x10aux::ref<x10::lang::Rail<FMGL(T) > > src,
      x10_int src_off,
      x10aux::ref<x10::lang::Rail<FMGL(T) > > dst,
      x10_int dst_off,
      x10_int count);
    template<class FMGL(T)>
    static void
      alltoall(
      x10::util::Team this_, x10_int role,
      x10aux::ref<x10::array::Array<FMGL(T)> > src,
      x10_int src_off,
      x10aux::ref<x10::array::Array<FMGL(T)> > dst,
      x10_int dst_off,
      x10_int count);
    template<class FMGL(T)>
    static void
      nativeAlltoall(
      x10_int id,
      x10_int role,
      x10::util::IndexedMemoryChunk<FMGL(T) > src,
      x10_int src_off,
      x10::util::IndexedMemoryChunk<FMGL(T) > dst,
      x10_int dst_off,
      x10_int count);
    static x10_int
      FMGL(ADD);
    
    static inline x10_int
      FMGL(ADD__get)() {
        return x10::util::Team_methods::FMGL(ADD);
    }
    static x10_int
      FMGL(MUL);
    
    static inline x10_int
      FMGL(MUL__get)() {
        return x10::util::Team_methods::FMGL(MUL);
    }
    static x10_int
      FMGL(AND);
    
    static inline x10_int
      FMGL(AND__get)() {
        return x10::util::Team_methods::FMGL(AND);
    }
    static x10_int
      FMGL(OR);
    
    static inline x10_int
      FMGL(OR__get)() {
        return x10::util::Team_methods::FMGL(OR);
    }
    static x10_int
      FMGL(XOR);
    
    static inline x10_int
      FMGL(XOR__get)() {
        return x10::util::Team_methods::FMGL(XOR);
    }
    static x10_int
      FMGL(MAX);
    
    static inline x10_int
      FMGL(MAX__get)() {
        return x10::util::Team_methods::FMGL(MAX);
    }
    static x10_int
      FMGL(MIN);
    
    static inline x10_int
      FMGL(MIN__get)() {
        return x10::util::Team_methods::FMGL(MIN);
    }
    template<class FMGL(T)>
    static void
      allreduce(
      x10::util::Team this_, x10_int role,
      x10aux::ref<x10::lang::Rail<FMGL(T) > > src,
      x10_int src_off,
      x10aux::ref<x10::lang::Rail<FMGL(T) > > dst,
      x10_int dst_off,
      x10_int count,
      x10_int op);
    template<class FMGL(T)>
    static void
      allreduce(
      x10::util::Team this_, x10_int role,
      x10aux::ref<x10::array::Array<FMGL(T)> > src,
      x10_int src_off,
      x10aux::ref<x10::array::Array<FMGL(T)> > dst,
      x10_int dst_off,
      x10_int count,
      x10_int op);
    template<class FMGL(T)>
    static void
      nativeAllreduce(
      x10_int id,
      x10_int role,
      x10::util::IndexedMemoryChunk<FMGL(T) > src,
      x10_int src_off,
      x10::util::IndexedMemoryChunk<FMGL(T) > dst,
      x10_int dst_off,
      x10_int count,
      x10_int op);
    static x10_byte
      allreduce(
      x10::util::Team this_, x10_int role,
      x10_byte src,
      x10_int op) {
        
        //#line 234 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return x10::util::Team_methods::genericAllreduce<x10_byte >(this_, 
                 role,
                 src,
                 op);
        
    }
    static x10_ubyte
      allreduce(
      x10::util::Team this_, x10_int role,
      x10_ubyte src,
      x10_int op) {
        
        //#line 236 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return x10::util::Team_methods::genericAllreduce<x10_ubyte >(this_, 
                 role,
                 src,
                 op);
        
    }
    static x10_short
      allreduce(
      x10::util::Team this_, x10_int role,
      x10_short src,
      x10_int op) {
        
        //#line 238 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return x10::util::Team_methods::genericAllreduce<x10_short >(this_, 
                 role,
                 src,
                 op);
        
    }
    static x10_ushort
      allreduce(
      x10::util::Team this_, x10_int role,
      x10_ushort src,
      x10_int op) {
        
        //#line 240 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return x10::util::Team_methods::genericAllreduce<x10_ushort >(this_, 
                 role,
                 src,
                 op);
        
    }
    static x10_uint
      allreduce(
      x10::util::Team this_, x10_int role,
      x10_uint src,
      x10_int op) {
        
        //#line 242 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return x10::util::Team_methods::genericAllreduce<x10_uint >(this_, 
                 role,
                 src,
                 op);
        
    }
    static x10_int
      allreduce(
      x10::util::Team this_, x10_int role,
      x10_int src,
      x10_int op) {
        
        //#line 244 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return x10::util::Team_methods::genericAllreduce<x10_int >(this_, 
                 role,
                 src,
                 op);
        
    }
    static x10_long
      allreduce(
      x10::util::Team this_, x10_int role,
      x10_long src,
      x10_int op) {
        
        //#line 246 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return x10::util::Team_methods::genericAllreduce<x10_long >(this_, 
                 role,
                 src,
                 op);
        
    }
    static x10_ulong
      allreduce(
      x10::util::Team this_, x10_int role,
      x10_ulong src,
      x10_int op) {
        
        //#line 248 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return x10::util::Team_methods::genericAllreduce<x10_ulong >(this_, 
                 role,
                 src,
                 op);
        
    }
    static x10_float
      allreduce(
      x10::util::Team this_, x10_int role,
      x10_float src,
      x10_int op) {
        
        //#line 250 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return x10::util::Team_methods::genericAllreduce<x10_float >(this_, 
                 role,
                 src,
                 op);
        
    }
    static x10_double
      allreduce(
      x10::util::Team this_, x10_int role,
      x10_double src,
      x10_int op) {
        
        //#line 252 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return x10::util::Team_methods::genericAllreduce<x10_double >(this_, 
                 role,
                 src,
                 op);
        
    }
    template<class FMGL(T)>
    static FMGL(T)
      genericAllreduce(
      x10::util::Team this_, x10_int role,
      FMGL(T) src,
      x10_int op);
    template<class FMGL(T)>
    static void
      nativeAllreduce(
      x10_int id,
      x10_int role,
      x10::util::IndexedMemoryChunk<FMGL(T) > src,
      x10::util::IndexedMemoryChunk<FMGL(T) > dst,
      x10_int op);
    static x10_int
      indexOfMax(
      x10::util::Team this_, x10_int role,
      x10_double v,
      x10_int idx);
    static void
      nativeIndexOfMax(
      x10_int id,
      x10_int role,
      x10::util::IndexedMemoryChunk<x10::util::Team__DoubleIdx > src,
      x10::util::IndexedMemoryChunk<x10::util::Team__DoubleIdx > dst);
    static x10_int
      indexOfMin(
      x10::util::Team this_, x10_int role,
      x10_double v,
      x10_int idx);
    static void
      nativeIndexOfMin(
      x10_int id,
      x10_int role,
      x10::util::IndexedMemoryChunk<x10::util::Team__DoubleIdx > src,
      x10::util::IndexedMemoryChunk<x10::util::Team__DoubleIdx > dst);
    static x10::util::Team
      split(
      x10::util::Team this_, x10_int role,
      x10_int color,
      x10_int new_role);
    static void
      nativeSplit(
      x10_int id,
      x10_int role,
      x10_int color,
      x10_int new_role,
      x10::util::IndexedMemoryChunk<x10_int > result);
    static void
      del(
      x10::util::Team this_, x10_int role);
    static void
      nativeDel(
      x10_int id,
      x10_int role);
    static x10aux::ref<x10::lang::String>
      toString(
      x10::util::Team this_);
    static x10_boolean
      equals(
      x10::util::Team this_, x10::util::Team that) {
        
        //#line 336 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(that->
                                        FMGL(id),
                                      this_->
                                        FMGL(id)));
        
    }
    static x10_boolean
      equals(
      x10::util::Team this_, x10aux::ref<x10::lang::Any> that);
    static x10_int
      hashCode(
      x10::util::Team this_) {
        
        //#line 338 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return this_->
                 FMGL(id);
        
    }
    static x10_boolean
      _struct_equals(
      x10::util::Team this_, x10aux::ref<x10::lang::Any> other);
    static x10_boolean
      _struct_equals(
      x10::util::Team this_, x10::util::Team other) {
        
        //#line 28 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(this_->
                                        FMGL(id),
                                      other->
                                        FMGL(id)));
        
    }
    static x10::util::Team
      x10__util__Team____x10__util__Team__this(
      x10::util::Team this_) {
        
        //#line 28 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
        return this_;
        
    }
    
};

} } 
#endif // X10_UTIL_TEAM_H

namespace x10 { namespace util { 
class Team;
} } 

#ifndef X10_UTIL_TEAM_H_NODEPS
#define X10_UTIL_TEAM_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/Any.h>
#include <x10/lang/Int.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Place.h>
#include <x10/array/Array.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/FinishState.h>
#include <x10/lang/Throwable.h>
#include <x10/lang/RuntimeException.h>
#include <x10/compiler/Finalization.h>
#include <x10/compiler/Native.h>
#include <x10/lang/Rail.h>
#include <x10/array/Array.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/lang/Rail.h>
#include <x10/array/Array.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/lang/Rail.h>
#include <x10/array/Array.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/lang/Rail.h>
#include <x10/array/Array.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/lang/Byte.h>
#include <x10/lang/UByte.h>
#include <x10/lang/Short.h>
#include <x10/lang/UShort.h>
#include <x10/lang/UInt.h>
#include <x10/lang/Long.h>
#include <x10/lang/ULong.h>
#include <x10/lang/Float.h>
#include <x10/lang/Double.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/util/IndexedMemoryChunk.h>
#include <x10/util/Team__DoubleIdx.h>
#include <x10/lang/IllegalArgumentException.h>
#include <x10/lang/String.h>
#include <x10/lang/Boolean.h>
#include <x10/compiler/NonEscaping.h>
#ifndef X10_UTIL_TEAM_H_GENERICS
#define X10_UTIL_TEAM_H_GENERICS
#ifndef X10_UTIL_TEAM_H_scatter_1801
#define X10_UTIL_TEAM_H_scatter_1801
template<class FMGL(T)> void x10::util::Team_methods::scatter(x10::util::Team this_, x10_int role,
                                                              x10_int root,
                                                              x10aux::ref<x10::lang::Rail<FMGL(T) > > src,
                                                              x10_int src_off,
                                                              x10aux::ref<x10::lang::Rail<FMGL(T) > > dst,
                                                              x10_int dst_off,
                                                              x10_int count) {
    {
        
        //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
        x10::lang::Runtime::ensureNotInAtomic();
        
        //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::FinishState> x10____var14 =
          x10::lang::Runtime::startFinish();
        {
            
            //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Throwable> throwable2196 =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(X10_NULL);
            
            //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
            try {
                
                //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
                try {
                    {
                        
                        //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        x10::util::Team_methods::nativeScatter<FMGL(T) >(
                          this_->
                            FMGL(id),
                          role,
                          root,
                          (src)->indexedMemoryChunk(),
                          src_off,
                          (dst)->indexedMemoryChunk(),
                          dst_off,
                          count);
                    }
                }
                catch (x10aux::__ref& __ref__1802) {
                    x10aux::ref<x10::lang::Throwable>& __exc__ref__1802 = (x10aux::ref<x10::lang::Throwable>&)__ref__1802;
                    if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1802)) {
                        x10aux::ref<x10::lang::Throwable> __lowerer__var__14__ =
                          static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1802);
                        {
                            
                            //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                            x10::lang::Runtime::pushException(
                              __lowerer__var__14__);
                            
                            //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                            x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
                        }
                    } else
                    throw;
                }
                
                //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::compiler::Finalization::plausibleThrow();
            }
            catch (x10aux::__ref& __ref__1803) {
                x10aux::ref<x10::lang::Throwable>& __exc__ref__1803 = (x10aux::ref<x10::lang::Throwable>&)__ref__1803;
                if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1803)) {
                    x10aux::ref<x10::lang::Throwable> formal2197 =
                      static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1803);
                    {
                        
                        //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        throwable2196 =
                          formal2197;
                    }
                } else
                throw;
            }
            {
                
                //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::lang::Runtime::stopFinish(x10____var14);
            }
            
            //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL,
                                        throwable2196)))
            {
                
                //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
                if (!(x10aux::instanceof<x10aux::ref<x10::compiler::Finalization> >(throwable2196)))
                {
                    
                    //#line 109 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(throwable2196));
                }
                
            }
            
        }
    }
}
#endif // X10_UTIL_TEAM_H_scatter_1801
#ifndef X10_UTIL_TEAM_H_scatter_1804
#define X10_UTIL_TEAM_H_scatter_1804
template<class FMGL(T)> void x10::util::Team_methods::scatter(
  x10::util::Team this_, x10_int role,
  x10_int root,
  x10aux::ref<x10::array::Array<FMGL(T)> > src,
  x10_int src_off,
  x10aux::ref<x10::array::Array<FMGL(T)> > dst,
  x10_int dst_off,
  x10_int count) {
    {
        
        //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
        x10::lang::Runtime::ensureNotInAtomic();
        
        //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::FinishState> x10____var15 =
          x10::lang::Runtime::startFinish();
        {
            
            //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Throwable> throwable2199 =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(X10_NULL);
            
            //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
            try {
                
                //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
                try {
                    {
                        
                        //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        x10::util::Team_methods::nativeScatter<FMGL(T) >(
                          this_->
                            FMGL(id),
                          role,
                          root,
                          x10aux::nullCheck(src)->x10::array::Array<FMGL(T)>::raw(),
                          src_off,
                          x10aux::nullCheck(dst)->x10::array::Array<FMGL(T)>::raw(),
                          dst_off,
                          count);
                    }
                }
                catch (x10aux::__ref& __ref__1805) {
                    x10aux::ref<x10::lang::Throwable>& __exc__ref__1805 = (x10aux::ref<x10::lang::Throwable>&)__ref__1805;
                    if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1805)) {
                        x10aux::ref<x10::lang::Throwable> __lowerer__var__15__ =
                          static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1805);
                        {
                            
                            //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                            x10::lang::Runtime::pushException(
                              __lowerer__var__15__);
                            
                            //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                            x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
                        }
                    } else
                    throw;
                }
                
                //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::compiler::Finalization::plausibleThrow();
            }
            catch (x10aux::__ref& __ref__1806) {
                x10aux::ref<x10::lang::Throwable>& __exc__ref__1806 = (x10aux::ref<x10::lang::Throwable>&)__ref__1806;
                if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1806)) {
                    x10aux::ref<x10::lang::Throwable> formal2200 =
                      static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1806);
                    {
                        
                        //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        throwable2199 =
                          formal2200;
                    }
                } else
                throw;
            }
            {
                
                //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::lang::Runtime::stopFinish(
                  x10____var15);
            }
            
            //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL,
                                        throwable2199)))
            {
                
                //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
                if (!(x10aux::instanceof<x10aux::ref<x10::compiler::Finalization> >(throwable2199)))
                {
                    
                    //#line 113 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(throwable2199));
                }
                
            }
            
        }
    }
}
#endif // X10_UTIL_TEAM_H_scatter_1804
#ifndef X10_UTIL_TEAM_H_nativeScatter_1807
#define X10_UTIL_TEAM_H_nativeScatter_1807
template<class FMGL(T)> void x10::util::Team_methods::nativeScatter(
  x10_int id,
  x10_int role,
  x10_int root,
  x10::util::IndexedMemoryChunk<FMGL(T) > src,
  x10_int src_off,
  x10::util::IndexedMemoryChunk<FMGL(T) > dst,
  x10_int dst_off,
  x10_int count) {
    x10rt_scatter(id, role, root, &src->raw()[src_off], &dst->raw()[dst_off], sizeof(FMGL(T)), count, x10aux::coll_handler, x10aux::coll_enter());
}
#endif // X10_UTIL_TEAM_H_nativeScatter_1807
#ifndef X10_UTIL_TEAM_H_bcast_1808
#define X10_UTIL_TEAM_H_bcast_1808
template<class FMGL(T)> void x10::util::Team_methods::bcast(
  x10::util::Team this_, x10_int role,
  x10_int root,
  x10aux::ref<x10::lang::Rail<FMGL(T) > > src,
  x10_int src_off,
  x10aux::ref<x10::lang::Rail<FMGL(T) > > dst,
  x10_int dst_off,
  x10_int count) {
    {
        
        //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
        x10::lang::Runtime::ensureNotInAtomic();
        
        //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::FinishState> x10____var16 =
          x10::lang::Runtime::startFinish();
        {
            
            //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Throwable> throwable2202 =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(X10_NULL);
            
            //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
            try {
                
                //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
                try {
                    {
                        
                        //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        x10::util::Team_methods::nativeBcast<FMGL(T) >(
                          this_->
                            FMGL(id),
                          role,
                          root,
                          (src)->indexedMemoryChunk(),
                          src_off,
                          (dst)->indexedMemoryChunk(),
                          dst_off,
                          count);
                    }
                }
                catch (x10aux::__ref& __ref__1809) {
                    x10aux::ref<x10::lang::Throwable>& __exc__ref__1809 = (x10aux::ref<x10::lang::Throwable>&)__ref__1809;
                    if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1809)) {
                        x10aux::ref<x10::lang::Throwable> __lowerer__var__16__ =
                          static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1809);
                        {
                            
                            //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                            x10::lang::Runtime::pushException(
                              __lowerer__var__16__);
                            
                            //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                            x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
                        }
                    } else
                    throw;
                }
                
                //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::compiler::Finalization::plausibleThrow();
            }
            catch (x10aux::__ref& __ref__1810) {
                x10aux::ref<x10::lang::Throwable>& __exc__ref__1810 = (x10aux::ref<x10::lang::Throwable>&)__ref__1810;
                if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1810)) {
                    x10aux::ref<x10::lang::Throwable> formal2203 =
                      static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1810);
                    {
                        
                        //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        throwable2202 =
                          formal2203;
                    }
                } else
                throw;
            }
            {
                
                //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::lang::Runtime::stopFinish(
                  x10____var16);
            }
            
            //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL,
                                        throwable2202)))
            {
                
                //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
                if (!(x10aux::instanceof<x10aux::ref<x10::compiler::Finalization> >(throwable2202)))
                {
                    
                    //#line 138 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(throwable2202));
                }
                
            }
            
        }
    }
}
#endif // X10_UTIL_TEAM_H_bcast_1808
#ifndef X10_UTIL_TEAM_H_bcast_1811
#define X10_UTIL_TEAM_H_bcast_1811
template<class FMGL(T)> void x10::util::Team_methods::bcast(
  x10::util::Team this_, x10_int role,
  x10_int root,
  x10aux::ref<x10::array::Array<FMGL(T)> > src,
  x10_int src_off,
  x10aux::ref<x10::array::Array<FMGL(T)> > dst,
  x10_int dst_off,
  x10_int count) {
    {
        
        //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
        x10::lang::Runtime::ensureNotInAtomic();
        
        //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::FinishState> x10____var17 =
          x10::lang::Runtime::startFinish();
        {
            
            //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Throwable> throwable2205 =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(X10_NULL);
            
            //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
            try {
                
                //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
                try {
                    {
                        
                        //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        x10::util::Team_methods::nativeBcast<FMGL(T) >(
                          this_->
                            FMGL(id),
                          role,
                          root,
                          x10aux::nullCheck(src)->x10::array::Array<FMGL(T)>::raw(),
                          src_off,
                          x10aux::nullCheck(dst)->x10::array::Array<FMGL(T)>::raw(),
                          dst_off,
                          count);
                    }
                }
                catch (x10aux::__ref& __ref__1812) {
                    x10aux::ref<x10::lang::Throwable>& __exc__ref__1812 = (x10aux::ref<x10::lang::Throwable>&)__ref__1812;
                    if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1812)) {
                        x10aux::ref<x10::lang::Throwable> __lowerer__var__17__ =
                          static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1812);
                        {
                            
                            //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                            x10::lang::Runtime::pushException(
                              __lowerer__var__17__);
                            
                            //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                            x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
                        }
                    } else
                    throw;
                }
                
                //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::compiler::Finalization::plausibleThrow();
            }
            catch (x10aux::__ref& __ref__1813) {
                x10aux::ref<x10::lang::Throwable>& __exc__ref__1813 = (x10aux::ref<x10::lang::Throwable>&)__ref__1813;
                if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1813)) {
                    x10aux::ref<x10::lang::Throwable> formal2206 =
                      static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1813);
                    {
                        
                        //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        throwable2205 =
                          formal2206;
                    }
                } else
                throw;
            }
            {
                
                //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::lang::Runtime::stopFinish(
                  x10____var17);
            }
            
            //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL,
                                        throwable2205)))
            {
                
                //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
                if (!(x10aux::instanceof<x10aux::ref<x10::compiler::Finalization> >(throwable2205)))
                {
                    
                    //#line 142 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(throwable2205));
                }
                
            }
            
        }
    }
}
#endif // X10_UTIL_TEAM_H_bcast_1811
#ifndef X10_UTIL_TEAM_H_nativeBcast_1814
#define X10_UTIL_TEAM_H_nativeBcast_1814
template<class FMGL(T)> void x10::util::Team_methods::nativeBcast(
  x10_int id,
  x10_int role,
  x10_int root,
  x10::util::IndexedMemoryChunk<FMGL(T) > src,
  x10_int src_off,
  x10::util::IndexedMemoryChunk<FMGL(T) > dst,
  x10_int dst_off,
  x10_int count) {
    x10rt_bcast(id, role, root, &src->raw()[src_off], &dst->raw()[dst_off], sizeof(FMGL(T)), count, x10aux::coll_handler, x10aux::coll_enter());
}
#endif // X10_UTIL_TEAM_H_nativeBcast_1814
#ifndef X10_UTIL_TEAM_H_alltoall_1815
#define X10_UTIL_TEAM_H_alltoall_1815
template<class FMGL(T)> void x10::util::Team_methods::alltoall(
  x10::util::Team this_, x10_int role,
  x10aux::ref<x10::lang::Rail<FMGL(T) > > src,
  x10_int src_off,
  x10aux::ref<x10::lang::Rail<FMGL(T) > > dst,
  x10_int dst_off,
  x10_int count) {
    {
        
        //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
        x10::lang::Runtime::ensureNotInAtomic();
        
        //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::FinishState> x10____var18 =
          x10::lang::Runtime::startFinish();
        {
            
            //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Throwable> throwable2208 =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(X10_NULL);
            
            //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
            try {
                
                //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
                try {
                    {
                        
                        //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        x10::util::Team_methods::nativeAlltoall<FMGL(T) >(
                          this_->
                            FMGL(id),
                          role,
                          (src)->indexedMemoryChunk(),
                          src_off,
                          (dst)->indexedMemoryChunk(),
                          dst_off,
                          count);
                    }
                }
                catch (x10aux::__ref& __ref__1816) {
                    x10aux::ref<x10::lang::Throwable>& __exc__ref__1816 = (x10aux::ref<x10::lang::Throwable>&)__ref__1816;
                    if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1816)) {
                        x10aux::ref<x10::lang::Throwable> __lowerer__var__18__ =
                          static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1816);
                        {
                            
                            //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                            x10::lang::Runtime::pushException(
                              __lowerer__var__18__);
                            
                            //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                            x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
                        }
                    } else
                    throw;
                }
                
                //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::compiler::Finalization::plausibleThrow();
            }
            catch (x10aux::__ref& __ref__1817) {
                x10aux::ref<x10::lang::Throwable>& __exc__ref__1817 = (x10aux::ref<x10::lang::Throwable>&)__ref__1817;
                if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1817)) {
                    x10aux::ref<x10::lang::Throwable> formal2209 =
                      static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1817);
                    {
                        
                        //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        throwable2208 =
                          formal2209;
                    }
                } else
                throw;
            }
            {
                
                //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::lang::Runtime::stopFinish(
                  x10____var18);
            }
            
            //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL,
                                        throwable2208)))
            {
                
                //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
                if (!(x10aux::instanceof<x10aux::ref<x10::compiler::Finalization> >(throwable2208)))
                {
                    
                    //#line 170 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(throwable2208));
                }
                
            }
            
        }
    }
}
#endif // X10_UTIL_TEAM_H_alltoall_1815
#ifndef X10_UTIL_TEAM_H_alltoall_1818
#define X10_UTIL_TEAM_H_alltoall_1818
template<class FMGL(T)> void x10::util::Team_methods::alltoall(
  x10::util::Team this_, x10_int role,
  x10aux::ref<x10::array::Array<FMGL(T)> > src,
  x10_int src_off,
  x10aux::ref<x10::array::Array<FMGL(T)> > dst,
  x10_int dst_off,
  x10_int count) {
    {
        
        //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
        x10::lang::Runtime::ensureNotInAtomic();
        
        //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::FinishState> x10____var19 =
          x10::lang::Runtime::startFinish();
        {
            
            //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Throwable> throwable2211 =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(X10_NULL);
            
            //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
            try {
                
                //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
                try {
                    {
                        
                        //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        x10::util::Team_methods::nativeAlltoall<FMGL(T) >(
                          this_->
                            FMGL(id),
                          role,
                          x10aux::nullCheck(src)->x10::array::Array<FMGL(T)>::raw(),
                          src_off,
                          x10aux::nullCheck(dst)->x10::array::Array<FMGL(T)>::raw(),
                          dst_off,
                          count);
                    }
                }
                catch (x10aux::__ref& __ref__1819) {
                    x10aux::ref<x10::lang::Throwable>& __exc__ref__1819 = (x10aux::ref<x10::lang::Throwable>&)__ref__1819;
                    if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1819)) {
                        x10aux::ref<x10::lang::Throwable> __lowerer__var__19__ =
                          static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1819);
                        {
                            
                            //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                            x10::lang::Runtime::pushException(
                              __lowerer__var__19__);
                            
                            //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                            x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
                        }
                    } else
                    throw;
                }
                
                //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::compiler::Finalization::plausibleThrow();
            }
            catch (x10aux::__ref& __ref__1820) {
                x10aux::ref<x10::lang::Throwable>& __exc__ref__1820 = (x10aux::ref<x10::lang::Throwable>&)__ref__1820;
                if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1820)) {
                    x10aux::ref<x10::lang::Throwable> formal2212 =
                      static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1820);
                    {
                        
                        //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        throwable2211 =
                          formal2212;
                    }
                } else
                throw;
            }
            {
                
                //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::lang::Runtime::stopFinish(
                  x10____var19);
            }
            
            //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL,
                                        throwable2211)))
            {
                
                //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
                if (!(x10aux::instanceof<x10aux::ref<x10::compiler::Finalization> >(throwable2211)))
                {
                    
                    //#line 174 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(throwable2211));
                }
                
            }
            
        }
    }
}
#endif // X10_UTIL_TEAM_H_alltoall_1818
#ifndef X10_UTIL_TEAM_H_nativeAlltoall_1821
#define X10_UTIL_TEAM_H_nativeAlltoall_1821
template<class FMGL(T)> void x10::util::Team_methods::nativeAlltoall(
  x10_int id,
  x10_int role,
  x10::util::IndexedMemoryChunk<FMGL(T) > src,
  x10_int src_off,
  x10::util::IndexedMemoryChunk<FMGL(T) > dst,
  x10_int dst_off,
  x10_int count) {
    x10rt_alltoall(id, role, &src->raw()[src_off], &dst->raw()[dst_off], sizeof(FMGL(T)), count, x10aux::coll_handler, x10aux::coll_enter());
}
#endif // X10_UTIL_TEAM_H_nativeAlltoall_1821
#ifndef X10_UTIL_TEAM_H_allreduce_1822
#define X10_UTIL_TEAM_H_allreduce_1822
template<class FMGL(T)> void x10::util::Team_methods::allreduce(
  x10::util::Team this_, x10_int role,
  x10aux::ref<x10::lang::Rail<FMGL(T) > > src,
  x10_int src_off,
  x10aux::ref<x10::lang::Rail<FMGL(T) > > dst,
  x10_int dst_off,
  x10_int count,
  x10_int op) {
    {
        
        //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
        x10::lang::Runtime::ensureNotInAtomic();
        
        //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::FinishState> x10____var20 =
          x10::lang::Runtime::startFinish();
        {
            
            //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Throwable> throwable2214 =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(X10_NULL);
            
            //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
            try {
                
                //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
                try {
                    {
                        
                        //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        x10::util::Team_methods::nativeAllreduce<FMGL(T) >(
                          this_->
                            FMGL(id),
                          role,
                          (src)->indexedMemoryChunk(),
                          src_off,
                          (dst)->indexedMemoryChunk(),
                          dst_off,
                          count,
                          op);
                    }
                }
                catch (x10aux::__ref& __ref__1823) {
                    x10aux::ref<x10::lang::Throwable>& __exc__ref__1823 = (x10aux::ref<x10::lang::Throwable>&)__ref__1823;
                    if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1823)) {
                        x10aux::ref<x10::lang::Throwable> __lowerer__var__20__ =
                          static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1823);
                        {
                            
                            //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                            x10::lang::Runtime::pushException(
                              __lowerer__var__20__);
                            
                            //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                            x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
                        }
                    } else
                    throw;
                }
                
                //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::compiler::Finalization::plausibleThrow();
            }
            catch (x10aux::__ref& __ref__1824) {
                x10aux::ref<x10::lang::Throwable>& __exc__ref__1824 = (x10aux::ref<x10::lang::Throwable>&)__ref__1824;
                if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1824)) {
                    x10aux::ref<x10::lang::Throwable> formal2215 =
                      static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1824);
                    {
                        
                        //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        throwable2214 =
                          formal2215;
                    }
                } else
                throw;
            }
            {
                
                //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::lang::Runtime::stopFinish(
                  x10____var20);
            }
            
            //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL,
                                        throwable2214)))
            {
                
                //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
                if (!(x10aux::instanceof<x10aux::ref<x10::compiler::Finalization> >(throwable2214)))
                {
                    
                    //#line 221 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(throwable2214));
                }
                
            }
            
        }
    }
}
#endif // X10_UTIL_TEAM_H_allreduce_1822
#ifndef X10_UTIL_TEAM_H_allreduce_1825
#define X10_UTIL_TEAM_H_allreduce_1825
template<class FMGL(T)> void x10::util::Team_methods::allreduce(
  x10::util::Team this_, x10_int role,
  x10aux::ref<x10::array::Array<FMGL(T)> > src,
  x10_int src_off,
  x10aux::ref<x10::array::Array<FMGL(T)> > dst,
  x10_int dst_off,
  x10_int count,
  x10_int op) {
    {
        
        //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
        x10::lang::Runtime::ensureNotInAtomic();
        
        //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::FinishState> x10____var21 =
          x10::lang::Runtime::startFinish();
        {
            
            //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Throwable> throwable2217 =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(X10_NULL);
            
            //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
            try {
                
                //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
                try {
                    {
                        
                        //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        x10::util::Team_methods::nativeAllreduce<FMGL(T) >(
                          this_->
                            FMGL(id),
                          role,
                          x10aux::nullCheck(src)->x10::array::Array<FMGL(T)>::raw(),
                          src_off,
                          x10aux::nullCheck(dst)->x10::array::Array<FMGL(T)>::raw(),
                          dst_off,
                          count,
                          op);
                    }
                }
                catch (x10aux::__ref& __ref__1826) {
                    x10aux::ref<x10::lang::Throwable>& __exc__ref__1826 = (x10aux::ref<x10::lang::Throwable>&)__ref__1826;
                    if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1826)) {
                        x10aux::ref<x10::lang::Throwable> __lowerer__var__21__ =
                          static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1826);
                        {
                            
                            //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                            x10::lang::Runtime::pushException(
                              __lowerer__var__21__);
                            
                            //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                            x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
                        }
                    } else
                    throw;
                }
                
                //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::compiler::Finalization::plausibleThrow();
            }
            catch (x10aux::__ref& __ref__1827) {
                x10aux::ref<x10::lang::Throwable>& __exc__ref__1827 = (x10aux::ref<x10::lang::Throwable>&)__ref__1827;
                if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1827)) {
                    x10aux::ref<x10::lang::Throwable> formal2218 =
                      static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1827);
                    {
                        
                        //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        throwable2217 =
                          formal2218;
                    }
                } else
                throw;
            }
            {
                
                //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::lang::Runtime::stopFinish(
                  x10____var21);
            }
            
            //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL,
                                        throwable2217)))
            {
                
                //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
                if (!(x10aux::instanceof<x10aux::ref<x10::compiler::Finalization> >(throwable2217)))
                {
                    
                    //#line 225 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(throwable2217));
                }
                
            }
            
        }
    }
}
#endif // X10_UTIL_TEAM_H_allreduce_1825
#ifndef X10_UTIL_TEAM_H_nativeAllreduce_1828
#define X10_UTIL_TEAM_H_nativeAllreduce_1828
template<class FMGL(T)> void x10::util::Team_methods::nativeAllreduce(
  x10_int id,
  x10_int role,
  x10::util::IndexedMemoryChunk<FMGL(T) > src,
  x10_int src_off,
  x10::util::IndexedMemoryChunk<FMGL(T) > dst,
  x10_int dst_off,
  x10_int count,
  x10_int op) {
    x10rt_allreduce(id, role, &src->raw()[src_off], &dst->raw()[dst_off], (x10rt_red_op_type)op, x10rt_get_red_type<FMGL(T)>(), count, x10aux::coll_handler, x10aux::coll_enter());
}
#endif // X10_UTIL_TEAM_H_nativeAllreduce_1828
#ifndef X10_UTIL_TEAM_H_genericAllreduce_1839
#define X10_UTIL_TEAM_H_genericAllreduce_1839
template<class FMGL(T)> FMGL(T) x10::util::Team_methods::genericAllreduce(
  x10::util::Team this_, x10_int role,
  FMGL(T) src,
  x10_int op) {
    
    //#line 255 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
    x10::util::IndexedMemoryChunk<FMGL(T) > chk =
      x10::util::IndexedMemoryChunk<void>::allocate<FMGL(T) >(((x10_int)1), 8, false, false);
    
    //#line 256 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
    x10::util::IndexedMemoryChunk<FMGL(T) > dst =
      x10::util::IndexedMemoryChunk<void>::allocate<FMGL(T) >(((x10_int)1), 8, false, false);
    
    //#line 257 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
    (chk)->__set(src, ((x10_int)0));
    {
        
        //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
        x10::lang::Runtime::ensureNotInAtomic();
        
        //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::FinishState> x10____var22 =
          x10::lang::Runtime::startFinish();
        {
            
            //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Throwable> throwable2220 =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(X10_NULL);
            
            //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
            try {
                
                //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Try_c
                try {
                    {
                        
                        //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        x10::util::Team_methods::nativeAllreduce<FMGL(T) >(
                          this_->
                            FMGL(id),
                          role,
                          chk,
                          dst,
                          op);
                    }
                }
                catch (x10aux::__ref& __ref__1840) {
                    x10aux::ref<x10::lang::Throwable>& __exc__ref__1840 = (x10aux::ref<x10::lang::Throwable>&)__ref__1840;
                    if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1840)) {
                        x10aux::ref<x10::lang::Throwable> __lowerer__var__22__ =
                          static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1840);
                        {
                            
                            //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                            x10::lang::Runtime::pushException(
                              __lowerer__var__22__);
                            
                            //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                            x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
                        }
                    } else
                    throw;
                }
                
                //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::compiler::Finalization::plausibleThrow();
            }
            catch (x10aux::__ref& __ref__1841) {
                x10aux::ref<x10::lang::Throwable>& __exc__ref__1841 = (x10aux::ref<x10::lang::Throwable>&)__ref__1841;
                if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1841)) {
                    x10aux::ref<x10::lang::Throwable> formal2221 =
                      static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1841);
                    {
                        
                        //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                        throwable2220 =
                          formal2221;
                    }
                } else
                throw;
            }
            {
                
                //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Eval_c
                x10::lang::Runtime::stopFinish(
                  x10____var22);
            }
            
            //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL,
                                        throwable2220)))
            {
                
                //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10If_c
                if (!(x10aux::instanceof<x10aux::ref<x10::compiler::Finalization> >(throwable2220)))
                {
                    
                    //#line 258 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(throwable2220));
                }
                
            }
            
        }
    }
    
    //#line 259 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Team.x10": x10.ast.X10Return_c
    return (dst)->__apply(((x10_int)0));
    
}
#endif // X10_UTIL_TEAM_H_genericAllreduce_1839
#ifndef X10_UTIL_TEAM_H_nativeAllreduce_1842
#define X10_UTIL_TEAM_H_nativeAllreduce_1842
template<class FMGL(T)> void x10::util::Team_methods::nativeAllreduce(
  x10_int id,
  x10_int role,
  x10::util::IndexedMemoryChunk<FMGL(T) > src,
  x10::util::IndexedMemoryChunk<FMGL(T) > dst,
  x10_int op) {
    x10rt_allreduce(id, role, src->raw(), dst->raw(), (x10rt_red_op_type)op, x10rt_get_red_type<FMGL(T)>(), 1, x10aux::coll_handler, x10aux::coll_enter());
}
#endif // X10_UTIL_TEAM_H_nativeAllreduce_1842
#endif // X10_UTIL_TEAM_H_GENERICS
#endif // __X10_UTIL_TEAM_H_NODEPS
